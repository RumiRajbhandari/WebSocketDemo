import bookshelf from '../db';

const TABLE_NAME = 'inventory';

class Inventory extends bookshelf.Model{
    get tableName() {
        return TABLE_NAME
    }

    get hasTimestamps() {
        return false;
    }
}

export default Inventory;