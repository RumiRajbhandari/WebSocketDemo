import Inventory from '../models/inventory';

export function getAllInventory(){
    return Inventory.fetchAll();
}