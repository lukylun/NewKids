import React, { useState } from 'react';
import { IGetallVocaBody } from 'types/vocabulary';
import { checkVocabularyApi, deleteVocabularyApi } from 'utils/apis/vocabulary';
import Button from 'components/atoms/common/Button';
import { WordListItemWrapper } from './style';
import { ReactComponent as Delete } from '../../../../assets/icons/delete.svg';

interface WordListItemProps {
	items: IGetallVocaBody[];
}

function WordListItem({ items }: WordListItemProps) {
	const [selectedItem, setSelectedItem] = useState<IGetallVocaBody | undefined>(undefined);
	const [isOverlayOpen, setIsOverlayOpen] = useState(false);

	const handleItemClick = (item: IGetallVocaBody) => {
		setSelectedItem(item);
		setIsOverlayOpen(true);
	};

	const handleOverlayClose = () => {
		setIsOverlayOpen(false);
	};

	const handleDelete = async (vocabularyId: string) => {
		try {
			const response = await deleteVocabularyApi(vocabularyId);
			if (response.status === 200) {
				alert('나만의 단어장에서 삭제되었습니다.');
				setIsOverlayOpen(false);
				const allVocabularyEvent = new Event('allVocabulary');
				window.dispatchEvent(allVocabularyEvent);
				const checkVocabularyEvent = new Event('checkVocabulary');
				window.dispatchEvent(checkVocabularyEvent);
				const reSearchPageEvent = new Event('reSearchPage');
				window.dispatchEvent(reSearchPageEvent);
			}
		} catch (error) {
			console.error('삭제 오류발생');
		}
	};

	const handleClick = async (vocabularyId: string, checked: boolean) => {
		if (!checked) {
			try {
				const response = await checkVocabularyApi(vocabularyId);
				if (response.status === 200) {
					alert('아는 단어에 등록되었습니다.');
					const checkVocabularyEvent = new Event('checkVocabulary');
					window.dispatchEvent(checkVocabularyEvent);
				}
			} catch (error) {
				console.error('알아요 체크 오류');
			}
		} else if (checked) {
			try {
				const response = await checkVocabularyApi(vocabularyId);
				if (response.status === 200) {
					alert('아는 단어에서 등록취소되었습니다.');
					const checkVocabularyEvent = new Event('checkVocabulary');
					window.dispatchEvent(checkVocabularyEvent);
				}
			} catch (error) {
				console.error('알아요 체크 오류');
			}
		}
	};

	return (
		<WordListItemWrapper>
			{items.map((item) => (
				<div className="word-list-item-wrapper" key={item.vocabularyId}>
					<div className="word-list-item">
						<p className="item-vocabulary-content" onClick={() => handleItemClick(item)} role="presentation">
							{item.content}
						</p>
						{isOverlayOpen && selectedItem !== undefined && (
							<div className="overlay">
								<div className="overlay-content">
									<div className="overlay-header">
										<p className="selected-item-content">{selectedItem.content}</p>
										<div className="delete-button-wrapper">
											<Delete onClick={async () => handleDelete(selectedItem.vocabularyId.toString())} />
										</div>
									</div>
									<p className="selected-item-description">(뜻) {selectedItem.description}</p>
									<Button color="Primary" size="s" text="닫기" radius="m" handleClick={handleOverlayClose} />
								</div>
							</div>
						)}
						<div className="check-box">
							<input
								type="checkbox"
								id="input"
								onClick={async () => handleClick(item.vocabularyId.toString(), item.checked)}
								defaultChecked={item.checked}
							/>
						</div>
					</div>
					<div className="word-item-description">
						(뜻) {item.description.length > 10 ? `${item.description.slice(0, 10)}...` : item.description}
					</div>
				</div>
			))}
		</WordListItemWrapper>
	);
}

export default WordListItem;
