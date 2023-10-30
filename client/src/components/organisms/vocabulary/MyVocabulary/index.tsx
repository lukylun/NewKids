import React from 'react';
import WordListItem from 'components/atoms/vocabulary/WordListItem';
import { IGetallVocaBody } from 'types/vocabulary';
import { MyVocabularyContainer } from './style';

interface IMyVocabularyProps {
	vocabularys: IGetallVocaBody[];
}

function createRingElements(numberOfRings: number, gapPercentage: number) {
	const ringElements = [];

	for (let i = 1; i < numberOfRings; i += 1) {
		const leftPosition = `${i * gapPercentage}%`;
		const ring = (
			<div key={i}>
				<div className="ring-border" style={{ left: leftPosition }} />
				<div className="ring-border-1" style={{ left: leftPosition }} />
				<div className="ring" style={{ left: leftPosition }}>
					<div className="small-ring" />
				</div>
			</div>
		);
		ringElements.push(ring);
	}

	return ringElements;
}

function MyVocabulary(props: IMyVocabularyProps) {
	const { vocabularys } = props;
	const numberOfRings = 10; // 원하는 고리의 수
	const ringElements = [];

	for (let i = 1; i < numberOfRings; i += 1) {
		const leftPosition = `${i * 10}%`; // 원하는 간격을 백분율로 설정

		ringElements.push(<div className="ring" key={i} style={{ left: leftPosition }} />);
	}

	const leftVocaList: IGetallVocaBody[] = [];
	const rightVocaList: IGetallVocaBody[] = [];

	vocabularys.forEach((item, idx) => {
		if (idx % 2 === 0 && idx < 10) {
			leftVocaList.push(item);
		} else if (idx % 2 && idx < 10) {
			rightVocaList.push(item);
		}
	});

	return (
		<MyVocabularyContainer>
			<div className="notebook-size">
				<hr className="vertical-line" />
				{createRingElements(10, 10)}
				<div className="voca-book">
					<div className="left-page">
						<div className="left-title">
							<p>단어</p>
							<p>알고있어요!</p>
						</div>
						<hr />
						<div className="word-list">
							<WordListItem items={leftVocaList} />
						</div>
					</div>
					<div className="right-page">
						<div className="right-title">
							<p>단어</p>
							<p>알고있어요!</p>
						</div>
						<hr />
						<div className="word-list">
							<WordListItem items={rightVocaList} />
						</div>
					</div>
				</div>
			</div>
		</MyVocabularyContainer>
	);
}

export default MyVocabulary;
