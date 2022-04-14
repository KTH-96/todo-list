class Board {
  render(columns) {
    document.querySelector('main').insertAdjacentHTML('afterbegin', this.template(columns));
  }

  template(columns) {
    return `<div class="column-wrap">
        ${columns}
      </div>`;
  }

  addEvent(_this) {
    const $main = document.querySelector('main');
    $main.addEventListener('click', _this.btnClickHandler.bind(_this));
    $main.addEventListener('input', _this.cardInputHandler.bind(_this));
  }
}

export { Board };
