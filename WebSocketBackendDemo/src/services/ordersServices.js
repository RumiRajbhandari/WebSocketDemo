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
    
    const stock_quantity = await getStockQuantity(ordersModel.sku_id)
    console.log('stock quantity', stock_quantity)
    if(stock_quantity >= ordersModel.quantity){
        createOrders(ordersModel)
        updateStock(ordersModel.sku_id, (stock_quantity-ordersModel.quantity))
        return 'Order placed'
    }
    else{
        return 'Quantity exceeded'
    }
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
     .update({quantity: quantity})
}