import * as inventoryService from '../services/inventoryServices';

export function fetchAll(req, res, next){
    return inventoryService
    .getAllInventory()
    .then(data => res.json({ data }))
    .catch(err => next(err));
}