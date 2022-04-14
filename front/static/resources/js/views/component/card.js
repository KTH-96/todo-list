class Card {
  constructor(props) {
    this.props = props;
  }

  getFormElement(columnName) {
    return document.querySelector(`form[data-status=${columnName}]`);
  }

  getTitle($form) {
    return $form.querySelector('.card__title').value;
  }

  getContent($form) {
    return $form.querySelector('.card__content').value;
  }

  writableTemplate() {
    return `<li class="card card--write">
          <form name="writable-form" data-status="${this.props.cardStatus}">
            <input type="text" name="writable-form" maxlength="50" placeholder="제목을 입력하세요" class="card__title" />
            <textarea
              name="writable-form"
              maxlength="500"
              placeholder="내용을 입력하세요"
              class="card__content"
            ></textarea>
            <span class="card__writer">도니</span>
            <div class="button-wrap card__button-wrap">
              <button type="button" class="button--cancle card__button--cancle" data-status="${this.props.cardStatus}">취소</button>
              <button type="button" class="button--submit card__button--submit" data-status="${this.props.cardStatus}">등록</button>
            </div>
          </form>
        </li>`;
  }

  nomalTemplate() {
    return `<li class="card" data-id="${this.props.id}" data-status="${this.props.cardStatus}" data-index="${this.props.cardIndex}>
          <h3 class="card__title">${this.props.title}</h3>
          <div class="card__content">
            <p>${this.props.contents}</p>
          </div>
          <p class="card__writer">${this.props.writer}</p>
          <button type="button" class="button--delete card__button--delete">
            <span class="hidden-text">카드 삭제 버튼</span>
          </button>
        </li>`;
  }
}

export { Card };