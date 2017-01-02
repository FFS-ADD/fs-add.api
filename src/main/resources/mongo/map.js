function () {
	if(this.author){
		emit(this.author, 1);
	} else {
		emit('key', 1);
	}
}