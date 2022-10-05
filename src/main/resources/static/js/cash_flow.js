var ctx = document.getElementById("myChart").getContext('2d');
const now_status_btn = document.getElementById("left");
const bottomIconFirst = document.getElementById("bottom-icon-first")
const bottomIconSecond = document.getElementById("bottom-icon-second")
const bottomIconThird = document.getElementById("bottom-icon-third")

bottomIconFirst.onclick = () => {
  location.href = "/";
}

bottomIconSecond.onclick = () => {
location.href = "/cost-manage";
}

bottomIconThird.onclick = ()=>{
  location.href = "/bankruptcy-prevent"
}
now_status_btn.onclick = ()=>{
  location.href = "/"
}

function draw() {
    ctx.font = '48px serif';
    ctx.fillText('Hello world', 10, 50);
} 

var myChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    datasets: [{
      label: '# of Tomatoes',
      data: [35, 65],
      backgroundColor: [
        '#1DCB9D',
        '#F3F3F3'
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
        borderWidth: 1,
        rotation: -90,
        circumference: 180,
  }
}
);

