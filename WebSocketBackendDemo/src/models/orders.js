import bookshelf from '../db';

const TABLE_NAME = 'orders';

class Orders extends bookshelf.Model{
    get tableName() {
        return TABLE_NAME
    }

    get hasTimestamps() {
        return false;
    }
}

export default Orders;