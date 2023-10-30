import React, { useState, useEffect } from 'react';
import ReactWordcloud, { OptionsProp } from 'react-wordcloud';
import { IWordCloudKeyword, IWordCloudResponse } from 'types/keyword';
import { getPopularKeywordApi } from 'utils/apis/keyword';
import useMovePage from 'hooks/useMovePage';
import { WordCloudContainer } from './style';
import 'tippy.js/dist/tippy.css';
import 'tippy.js/animations/scale.css';

interface IWordKeyword {
	text: string;
	value: number;
}

function KeywordCloud() {
	const [wordCloudKeywords, setWordCloudKeywords] = useState<IWordCloudKeyword[]>([]);
	const [movePage] = useMovePage();
	const options: OptionsProp = {
		fontFamily: 'Pretendard',
		fontSizes: [20, 60],
	};

	const callbacks = {
		onWordClick: (word: IWordKeyword) => movePage(`/article?search=${word.text}`),
		getWordTooltip: (word: IWordKeyword) => `${word.text}`,
	};

	const fetchKeywordData = async () => {
		try {
			const response = await getPopularKeywordApi();

			if (response.status === 200) {
				const tmpArr = response.data.data.map((el: IWordCloudResponse) => ({
					keywordId: el.keywordId,
					text: el.word,
					value: el.totalCount,
				}));

				setWordCloudKeywords(tmpArr);
			}
		} catch (error) {
			console.error(error);
		}
	};

	useEffect(() => {
		fetchKeywordData();
	}, []);

	return (
		<WordCloudContainer>
			<ReactWordcloud words={wordCloudKeywords} options={options} callbacks={callbacks} />
		</WordCloudContainer>
	);
}

export default KeywordCloud;
