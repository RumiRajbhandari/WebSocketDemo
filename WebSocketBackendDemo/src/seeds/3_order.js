/**
 * Delete existing entries and seed values for `table_name`.
 *
 * @param   {object} knex
 * @returns {Promise}
 */
export function seed(knex) {
  return knex('orders')
    .del()
    .then(() => {
      return knex('orders').insert([
        {
          sku_id: 1,
          quantity: 12,
          outlet_id:1
        },
        {
          sku_id: 2,
          quantity: 2,
          outlet_id:2
        }
      ]);
    });
}
