/**
 * Delete existing entries and seed values for `table_name`.
 *
 * @param   {object} knex
 * @returns {Promise}
 */
export function seed(knex) {
  return knex('stocks')
    .del()
    .then(() => {
      return knex('stocks').insert([
        {
          sku_id: 1,
          quantity: 100
        },
        {
          sku_id: 2,
          quantity: 10
        },
        {
          sku_id: 3,
          quantity: 22
        },
        {
          sku_id: 4,
          quantity: 10
        },
        {
          sku_id: 5,
          quantity: 100
        }
      ]);
    });
}
