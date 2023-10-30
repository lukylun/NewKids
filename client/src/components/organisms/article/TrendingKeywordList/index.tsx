import React, { useState, useEffect } from 'react';
import KeywordListItem from 'components/atoms/common/KeywordListItem';
import { getPopularKeywordApi } from 'utils/apis/keyword';
import { IKeyword } from 'types/keyword';
import useMovePage from 'hooks/useMovePage';
import { TrendingKeywordListContainer } from './style';

function TrendingKeywordList() {
	const [keywords, setKeywords] = useState<IKeyword[]>([]);
	const [movePage] = useMovePage();

	const fetchData = async () => {
		try {
			const response = await getPopularKeywordApi();
			setKeywords(response.data.data);
		} catch (error) {
			console.error(error);
		}
	};
	useEffect(() => {
		fetchData();
	}, []);

	return (
		<TrendingKeywordListContainer>
			{keywords.length ? (
				keywords.map((el) => (
					<KeywordListItem
						key={el.keywordId}
						text={el.word}
						handleClick={() => movePage(`/article?search=${el.word}`)}
					/>
				))
			) : (
				<div>키워드가 없습니다</div>
			)}
		</TrendingKeywordListContainer>
	);
}

export default TrendingKeywordList;
