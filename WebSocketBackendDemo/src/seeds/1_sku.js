/**
 * Delete existing entries and seed values for `table_name`.
 *
 * @param   {object} knex
 * @returns {Promise}
 */
export function seed(knex) {
  return knex('skus')
    .del()
    .then(() => {
      return knex('skus').insert([
        {
          name: "Tiger"
        },
        {
          name: "Hearts biscuit"
        },
        {
          name: "Wai wai"
        },
        {
          name: "Rumpum"
        },
        {
          name: "Aqua"
        },
        {
          name: "icecream"
        }
      ]);
    });
}
