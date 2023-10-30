import React, { useState } from 'react';
import SearchBar from 'components/atoms/common/SearchBar';
import { getAllWordApi, registVocabularyApi } from 'utils/apis/vocabulary';
import { IWord } from 'types/keyword';
import useMovePage from 'hooks/useMovePage';
import { DictionaryContainer, WordListContainer, WordListItemContainer } from './style';

function WordListItem({ word, idx }: { word: IWord; idx: number }) {
	const [movePage] = useMovePage();

	const registWord = async () => {
		try {
			const memberKey = localStorage.getItem('memberkey');

			if (memberKey) {
				const body = {
					wordKey: word.wordKey,
				};

				const response = await registVocabularyApi(memberKey, body);

				if (response.status === 201) {
					alert(`'${word.content}'가 단어장에 추가되었습니다 !`);
				}
			} else {
				alert('로그인이 필요한 서비스입니다.');
				movePage('/auth/login');
			}
		} catch (error) {
			console.error(error);
		}
	};

	return (
		<WordListItemContainer>
			<span className="word-header">
				<b>{word.content}</b> 뜻 {idx + 1}
			</span>
			<span>{word.description}</span>
			<button className="add" type="button" onClick={registWord}>
				단어장에 추가하기
			</button>
		</WordListItemContainer>
	);
}
function Dictionary() {
	const [searchWord, setSearchWord] = useState('');
	const [wordList, setWordList] = useState<IWord[]>([]);
	const [message, setMessage] = useState('검색어를 입력하세요.');

	const search = async () => {
		if (searchWord) {
			try {
				const response = await getAllWordApi(searchWord, 1);

				if (response.data.data.content.length) {
					setWordList(response.data.data.content);
				} else {
					setWordList([]);
					setMessage(`'${searchWord}'에 대한 검색결과가 없습니다.`);
				}
			} catch (error) {
				setWordList([]);
				setMessage(`'${searchWord}'에 대한 검색결과가 없습니다.`);
				console.error(error);
			}
		}
	};

	return (
		<DictionaryContainer>
			<h3>뜻을 모르는 단어가 있나요?</h3>
			<SearchBar
				value={searchWord}
				setValue={setSearchWord}
				size="full"
				confirmSearch={search}
				placeholder="검색어를 입력하세요"
				color="SubSecond"
			/>
			<WordListContainer>
				{wordList.length ? (
					wordList.map((el, idx) => <WordListItem word={el} idx={idx} key={el.wordKey} />)
				) : (
					<div>{message}</div>
				)}
			</WordListContainer>
		</DictionaryContainer>
	);
}

export default Dictionary;
