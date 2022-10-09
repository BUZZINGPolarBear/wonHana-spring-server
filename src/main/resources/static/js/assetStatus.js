var ctx = document.getElementById("myChart").getContext('2d');
const cash_flow_btn = document.getElementById("right");
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

cash_flow_btn.onclick = ()=>{
  location.href = "/cash-flow"
}

window.onload = function(){
  drawIndex();
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

async function drawIndex(){
  const getUserInform = await getAPI('localhost:23628', 'userInfoById/1');
  // console.log(getUserInform.result[0]);

  const assetTotal = getUserInform.result[0].asset
  // console.log(assetTotal)

  const stock =  getUserInform.result[0].stock
  // console.log(`stock : ${((stock/assetTotal)*100).toFixed(2)}%`)
  const accountBalance =  getUserInform.result[0].accountBalance
  // console.log(`accountBalance : ${((accountBalance/assetTotal)*100).toFixed(2)}%`)
  const house =  getUserInform.result[0].house
  // console.log(`house : ${((house/assetTotal)*100).toFixed(2)}%`)
  const cash = assetTotal - (stock + accountBalance + house)
  // console.log(`cash : ${((cash/assetTotal)*100).toFixed(2)}%`)

  $("#stock-inform-as-ratio").append(`${((stock/assetTotal)*100).toFixed(2)}%`)
  $("#stock-inform-as-money").append(`${stock.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}원`)

  $("#balance-inform-as-ratio").append(`${((accountBalance/assetTotal)*100).toFixed(2)}%`)
  $("#balance-inform-as-money").append(`${accountBalance.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}원`)

  $("#cash-inform-as-ratio").append(`${((cash/assetTotal)*100).toFixed(2)}%`)
  $("#cash-inform-as-money").append(`${cash.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}원`)

  $("#house-inform-as-ratio").append(`${((house/assetTotal)*100).toFixed(2)}%`)
  $("#house-inform-as-money").append(`${house.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}원`)

  $(".draw-bar-graph#first-ratio").css("background-color", `#1DCB9D`)
  $(".draw-bar-graph#first-ratio").css("width", `${((stock/assetTotal)*100)-1}%`)

  $(".draw-bar-graph#second-ratio").css("background-color", `#4471FB`)
  $(".draw-bar-graph#second-ratio").css("width", `${((accountBalance/assetTotal)*100)-1}%`)

  $(".draw-bar-graph#third-ratio").css("background-color", `#FD6A51`)
  $(".draw-bar-graph#third-ratio").css("width", `${((cash/assetTotal)*100)-1}%`)

  $(".draw-bar-graph#fourth-ratio").css("background-color", `#FBAE29`)
  $(".draw-bar-graph#fourth-ratio").css("width", `${((house/assetTotal)*100)-1}%`)
}



//get API AS JSON
async function getAPI(host, path, headers ={}) {
  const url = `http://${host}/${path}`;
  const options = {
    method: "GET",
    headers: headers,
  };
  const res = await fetch(url, options);
  const data = res.json();
  // console.log(res)
  // console.log(data)
  if (res.ok) {
    return data;
  } else {
    throw new Error(data);
  }
}