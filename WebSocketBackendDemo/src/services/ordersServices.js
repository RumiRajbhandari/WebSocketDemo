import Orders from '../models/orders';
import knexJs from 'knex';
import knexConfig from '../knexfile';
import Stock from '../models/stocks';

const knex = knexJs(knexConfig);

export function getAllOrders(){
    return Orders.fetchAll();
}

export function createOrders( orders ){
    return new Orders({ sku_id: orders.sku_id, quantity: orders.quantity, outlet_id: orders.quantity }).save();
}

export function fetchById(id){
    return knex.raw('select * from orders where id = ?', [id])
    .then(data => data.rows);
}

export async function saveOrders(ordersModel){
   const receivedOrder = JSON.parse(ordersModel);
   return checkAndUpdateStock(receivedOrder)
   
}

export async function checkAndUpdateStock(ordersModel){
    
    const stock_available = await isStockQuantityAvailable(ordersModel.sku_id, ordersModel.quantity)
    if(stock_available){
        createOrders(ordersModel)
        updateStock(ordersModel.sku_id, ordersModel.quantity)
        const updatedQuantity = await getStockQuantity(ordersModel.sku_id)
        return {
            msg: 'Order placed',
            skuId: ordersModel.sku_id,
            outletId: ordersModel.outlet_id,
            quantity: updatedQuantity
        }
    }
    else{
        const updatedQuantity = await getStockQuantity(ordersModel.sku_id)
        return {
            msg: 'Quantity exceeded',
            skuId: ordersModel.sku_id,
            outletId: ordersModel.outlet_id,
            quantity: updatedQuantity
        }
    }
}

async function isStockQuantityAvailable(sku_id,qty){
    return new Stock().query(qb => {
        qb.where('sku_id', sku_id).andWhere('quantity', '>', qty)
    }).fetch().then(stock => {
        return stock ? true : false;
    })
    //return stock.toJSON().quantity;
}

async function getStockQuantity(sku_id){
    const stock = await  new Stock()
    .where('sku_id', sku_id)
    .query(qb => qb.column('quantity'))
    .fetch()
    return stock.toJSON().quantity;
}

async function updateStock(sku_id, quantity){
    await knex('stocks')
     .where({sku_id: sku_id})
     .decrement({quantity: quantity})
}