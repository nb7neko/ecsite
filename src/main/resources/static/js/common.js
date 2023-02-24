// ログイン
// index.htmlのloginが押されたら開始
let login = (event) => {
	// フォームが持つデフォルトの動作をキャンセルさせるメソッド（ページのリロードをキャンセル）
	event.preventDefault();
	// 入力されたアカウント情報をval()に設定
	let jsonString = {
		'userName': $('input[name=userName]').val(),
		'password': $('input[name=password]').val()
	};
	
	// 非同期通信で実行			
	$.ajax({
		type: 'POST',
		url: '/ecsite/api/login',
		// jsonStringに代入されている値をJSON文字列に変換する
		data: JSON.stringify(jsonString),
		// 送信するファイルの情報を知らせる
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	
	// 非同期通信の処理を受け継いで、後続処理に繋げる
	.then((result) => {
			let user = JSON.parse(result);
			// webページ内の文字をログインしているユーザー情報に書き換える
			$('#welcome').text(` -- ようこそ! ${user.fullName} さん`);
			$('#hiddenUserId').val(user.id);
			// 初期化
			$('input[name=userName]').val('');
			$('input[name=password]').val('');
		}, () => {
			console.error('Error: ajax connection failed.');
		}
	);
};
			
// カート情報
// index.htmlのaddCartが押されたら開始
let addCart = (event) => {
	// letを使い変数の宣言、宣言された要素の配列を探す
	let tdList = $(event.target).parent().parent().find('td');
	
	// 探した配列をそれぞれの変数に代入する
	let id = $(tdList[0]).text();
	let goodsName = $(tdList[1]).text();
	let price = $(tdList[2]).text();
	let count = $(tdList[3]).find('input').val();
	
	// 0もしくは空欄だと追加されない
	if(count === '0' || count === ''){
		alert('注文数が0または空欄です。');
		return;
	}
	
	//　変数をそれぞれ紐づける
	let cart = {
		'id': id,
		'goodsName': goodsName,
		'price': price,
		'count': count
	};
	// 新しく変数を作ってカート情報の配列を入れる
	cartList.push(cart);
	
	//　id=cartの<tbody>を探す
	let tbody = $('#cart').find('tbody');
	// <tbody>内に要素があったら消去
	$(tbody).children().remove();
	// 配列内の値をループさせなが取得する
	cartList.forEach(function(cart, index) {
		
		// 取得した値を表示させカートの中身を作成
		let tr = $('<tr />');			
		$('<td />', { 'text': cart.id }).appendTo(tr);
		$('<td />', { 'text': cart.goodsName }).appendTo(tr);
		$('<td />', { 'text': cart.price }).appendTo(tr);
		$('<td />', { 'text': cart.count }).appendTo(tr);
		let tdButton = $('<td />');
		$('<button />', {
			'text': 'カート消除',
			'class':'removeBtn',
		}).appendTo(tdButton);
		
		$(tdButton).appendTo(tr);
		$(tr).appendTo(tbody);
	});
	
	$('.removeBtn').on('click', removeCart);
};

// 購入処理

let buy = (event) => {
	$.ajax({
		type: 'POST',
		url: '/ecsite/api/purchase',
		data: JSON.stringify({
			"userId": $('#hiddenUserId').val(),
			"cartList": cartList
		}),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	.then((result) => {
		alert('購入しました。');
	}, () => {
		console.error('Error: ajax connection failed.');
	}
	);
};

// 消去

let removeCart = () => {
	const tdList = $(event.target).parent().parent().find('td');
	let id = $(tdList[0]).text();
	cartList = cartList.filter(function(cart) {
		return cart.id !== id;
	});
	$(event.target).parent().parent().remove();
};


// 履歴

let showHistory = () => {
	$.ajax({
		type: 'POST',
		url: '/ecsite/api/history',
		data: JSON.stringify({ "userId": $('#hiddenUserId').val() }),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	.then((result) => {
		let historyList = JSON.parse(result);
		let tbody = $('#historyTable').find('tbody');
		$(tbody).children().remove();
		historyList.forEach((history, index) => {
			let tr = $('<tr />');
			
			$('<td />', { 'text': history.goodsName }).appendTo(tr);
			$('<td />', { 'text': history.itemCount }).appendTo(tr);
			$('<td />', { 'text': history.createdAt }).appendTo(tr);
			
			$(tr).appendTo(tbody);
		});
		$("#history").dialog("open");
	}, () => {
		console.error('Error: ajax connection failed.');
	}
	);
};