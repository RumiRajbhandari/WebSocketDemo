/**
 * Delete existing entries and seed values for `table_name`.
 *
 * @param   {object} knex
 * @returns {Promise}
 */
exports.seed =  function(knex) {
  return knex('inventory')
    .del()
    .then(() => {
      return knex('inventory').insert([
        {
          sku_id: 1,
          sku_name: 'Tiger',
          quantity: 20,
          outlet_id: 1,
          outlet_name: "Ram store"
        },
        {
          sku_id: 2,
          sku_name: 'Hearts biscuit',
          quantity: 2,
          outlet_id: 2,
          outlet_name: "Shyam store"
        },
        {
          sku_id: 3,
          sku_name: 'Wai wai',
          quantity: 200,
          outlet_id: 3,
          outlet_name: "Hari store"
        },
        {
          sku_id: 4,
          sku_name: 'Rumpum',
          quantity: 0,
          outlet_id: 4,
          outlet_name: "Gita store"
        }
      ]);
    });
}
