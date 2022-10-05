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

var mySlider = new rSlider({
  target: '#slider',
  values: [2008, 2009, 2010, 2011],
  range: true, // range slider
  set:    null, // an array of preselected values
  width:    null,
  scale:    true,
  labels:   true,
  tooltip:  true,
  step:     null, // step size
  disabled: false, // is disabled?
  onChange: null // callback
});