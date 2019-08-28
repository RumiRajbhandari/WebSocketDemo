import bookshelf from '../db';

const TABLE_NAME = 'stocks';

class Stocks extends bookshelf.Model{
    get tableName() {
        return TABLE_NAME
    }

    get hasTimestamps() {
        return false;
    }
}

export default Stocks;