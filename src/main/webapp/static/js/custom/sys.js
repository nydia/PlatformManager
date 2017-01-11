/**
 * 系统Function函数
 * 
 * @param $
 */
(function() {
	var lhq = {};
	lhq.load_main_page = function(url) {
		$("#page-main").load(url, function(response, status, xhr) {
			// TODO
		});
	};
	
	window.lhq = lhq;
})();
