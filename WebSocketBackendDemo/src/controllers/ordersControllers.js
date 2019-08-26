import * as ordersService from '../services/ordersServices';

export function fetchAll(req, res, next){
    return ordersService
    .getAllOrders()
    .then(data => res.json({ data }))
    .catch(err => next(err));
}

export function saveOrders(req, res, next){

    console.log(`skuid is ${ req.body.sku_id }`);
    return ordersService
    .createOrders(req.body)
    .then(data => res.json({ data }))
    .catch(err => next(err));
}

export function fetchById(req, res, next){
    return ordersService
    .fetchById(req.query.id)
    .then(data => res.json({data}))
    .catch(err => next(err))
}