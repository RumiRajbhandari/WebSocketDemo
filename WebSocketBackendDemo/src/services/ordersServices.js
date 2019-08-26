import Orders from '../models/orders';
import knexJs from 'knex';
import knexConfig from '../knexfile';

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
