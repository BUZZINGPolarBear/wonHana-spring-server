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

window.onload = function(){
    drawIndex();
}

async function drawIndex() {
    const getUserInform = await getAPI(hostAddress, 'userInfoById/1');
    console.log(getUserInform.result[0]);

    const fixedExpenditure = getUserInform.result[0].fixedExpenditure;
    const flexibleExpenditure = getUserInform.result[0].flexibleExpenditure;
    const income = getUserInform.result[0].income;

    const fixedExpenditureRatio = (fixedExpenditure/income)*100;
    const flexibleExpenditureRatio = (flexibleExpenditure/income)*100;
    const usableMoneyRatio = 100-fixedExpenditureRatio-flexibleExpenditureRatio;

    var ctx = document.getElementById("myChart").getContext('2d');

    console.log(`고정지출 비율: ${Math.round(fixedExpenditureRatio)}`)
    console.log(`변동지출 비율: ${Math.round(flexibleExpenditureRatio)}`)
    console.log(`사용가능 금액 비율: ${Math.round(usableMoneyRatio)}`)

    $("#donut-percent-text").append(`${Math.round(fixedExpenditureRatio+flexibleExpenditureRatio)}%`)
    $("#usable-money-ratio").append(`${Math.round(usableMoneyRatio)}%`)
    $("#usable-money-won").append(`${(income - fixedExpenditure - flexibleExpenditure).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')}원`)

    $(".draw-bar-graph#first-ratio").css("background-color", `#2EB290`)
    $(".draw-bar-graph#first-ratio").css("width", `${((fixedExpenditureRatio/(fixedExpenditureRatio+flexibleExpenditureRatio))*100)-1}%`)
    $(".draw-bar-graph#fourth-ratio").css("background-color", `#3BD4AC`)
    $(".draw-bar-graph#fourth-ratio").css("width", `${((flexibleExpenditureRatio/(fixedExpenditureRatio+flexibleExpenditureRatio))*100)-1}%`)

    $("#fixed-expenditure-ratio-graph-top").append(`${Math.round((fixedExpenditureRatio/(fixedExpenditureRatio+flexibleExpenditureRatio))*100)-1}%`)
    $("#flexible-expenditure-ratio-graph-top").append(`${Math.round((flexibleExpenditureRatio/(fixedExpenditureRatio+flexibleExpenditureRatio))*100)-1}%`)
    $("#fixed-expenditure-ratio-table-top").append(`(${Math.round((fixedExpenditureRatio/(fixedExpenditureRatio+flexibleExpenditureRatio))*100)-1}%)`)
    $("#flexible-expenditure-ratio-table-top").append(`(${Math.round((flexibleExpenditureRatio/(fixedExpenditureRatio+flexibleExpenditureRatio))*100)-1}%)`)





    var myChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                datasets: [{
                    label: '# of Tomatoes',
                    data: [fixedExpenditureRatio, flexibleExpenditureRatio, usableMoneyRatio],
                    backgroundColor: [
                        '#2EB290',
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

