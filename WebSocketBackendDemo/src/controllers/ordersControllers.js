import * as ordersService from '../services/ordersServices';

export function fetchAll(req, res, next){
    return ordersService
    .getAllOrders()
    .then(data => res.json({ data }))
    .catch(err => next(err));
}