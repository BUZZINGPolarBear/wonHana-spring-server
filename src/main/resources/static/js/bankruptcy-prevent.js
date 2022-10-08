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

/*=================== [모바일 단 처리] ===================*/				
/* window.onload 사용해 터치 이벤트 발생 확인 : 상시 대기 */
window.onload = function() {						
	/* 터치 이벤트 감시 위한 객체 등록 및 이벤트 등록 실시 */
	var container_1 = document.getElementById('slider-touch-area');
			
	/* 터치 이벤트 등록 */
	container_1.addEventListener("touchmove", handleMove,false);
	container_1.addEventListener("touchend", handleEnd, false);
				
									
	/* 터치 이동 이벤트 발생 */
	function handleMove(evt) {				
		/* 시작 좌표값 확인 */
		beforeX = evt.
		beforeY = $(this).scrollTop();		
		console.log(`!!START!! AT X: ${beforeX} Y:${beforeY} `)		
	}
			
	/* 터치 종료 이벤트 발생 */
	function handleEnd(evt) {									
		/* 종료 좌표값 확인 */				
		var divX = evt.changedTouches[0].clientX;
		var divY = evt.changedTouches[0].clientY;								
				
		/* 강제로 특정 좌표값 클릭 이벤트 발생 수행 */
		console.log(`divX: ${divX} divY: ${divY}`)
	}						
};