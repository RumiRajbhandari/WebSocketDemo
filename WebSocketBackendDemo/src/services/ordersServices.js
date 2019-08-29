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
    
    const stock_available = await getStockQuantity(ordersModel.sku_id, ordersModel.quantity)
    if(stock_available){
        createOrders(ordersModel)
        updateStock(ordersModel.sku_id, ordersModel.quantity)
        return 'Order placed'
    }
    else{
        return 'Quantity exceeded'
    }
}

async function getStockQuantity(sku_id,qty){
    return new Stock().query(qb => {
        qb.where('sku_id', sku_id).andWhere('quantity', '>', qty)
    }).fetch().then(stock => {
        debugger;
        return stock ? true : false;
    })
    //return stock.toJSON().quantity;
}

async function updateStock(sku_id, quantity){
    await knex('stocks')
     .where({sku_id: sku_id})
     .decrement({quantity: quantity})
}