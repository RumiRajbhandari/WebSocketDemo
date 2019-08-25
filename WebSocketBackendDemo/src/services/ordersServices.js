import Orders from '../models/orders';

export function getAllOrders(){
    return Orders.fetchAll();
}