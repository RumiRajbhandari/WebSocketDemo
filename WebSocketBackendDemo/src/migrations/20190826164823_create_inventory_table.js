/**
 * Create table `table_name`.
 *
 * @param   {object} knex
 * @returns {Promise}
 */
exports.up =  function(knex) {
  return knex.schema.createTable('inventory', table => {
    table.increments();
    table.integer('sku_id').unsigned().notNullable().references('skus.id');
    table.integer('quantity').defaultTo(0);
    table.integer('outlet_id').notNullable();
    table.string('outlet_name').notNullable();
    table
      .timestamp('created_at')
      .notNull()
      .defaultTo(knex.raw('now()'));
    table
      .timestamp('updated_at')
      .notNull()
      .defaultTo(knex.raw('now()'));
      
  });
}

/**
 * Drop `table_name`.
 *
 * @param   {object} knex
 * @returns {Promise}
 */
exports.down = function(knex) {
  return knex.schema.dropTable('inventory');
}
