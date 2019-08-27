// Make connection
var socket = io.connect('http://localhost:4000');

//Query DOM
var sku_id = document.getElementById('sku_id'),
quantity = document.getElementById('quantity'),
outlet_id = document.getElementById('outlet_id'),
output = document.getElementById('output')
btn = document.getElementById('send')

//Emit events
btn.addEventListener('click', function(){
    // socket.emit('chatting', handle.value, message.value)
    const ordersModel = JSON.stringify({
        sku_id: sku_id.value,
        quantity: quantity.value,
        outlet_id: outlet_id.value
      })
    socket.emit('orders',ordersModel)
});

socket.on('orders', function(data){
    const ordersModel = JSON.stringify(data);
    output.innerHTML += '<p><strong>' + ordersModel+ ': </strong></p>'
})