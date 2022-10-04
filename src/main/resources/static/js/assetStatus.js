var ctx = document.getElementById("myChart").getContext('2d');

function draw() {
    ctx.font = '48px serif';
    ctx.fillText('Hello world', 10, 50);
} 

var myChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    datasets: [{
      label: '# of Tomatoes',
      data: [80, 20],
      backgroundColor: [
        '#FD6A51',
        '#E5E5E5'
      ],
      borderWidth: 1,
      weight: 10
    }]
  },
    options: {
        cutout: 60,
        elements: {
          center: {
            text: 'Red is 2/3 the total numbers',
            color: '#FF6384', // Default is #000000
            fontStyle: 'Arial', // Default is Arial
            sidePadding: 20, // Default is 20 (as a percentage)
            minFontSize: 20, // Default is 20 (in px), set to false and text will not wrap.
            lineHeight: 25 // Default is 25 (in px), used for when text wraps
          }
        },
              borderWidth: 1
  }
}
);

