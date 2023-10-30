import React, { useEffect, useState } from 'react';
import MyActivityChartGraph from 'components/atoms/myactivity/MyActivityChartGraph';
import MyActivityKeyword from 'components/atoms/myactivity/MyActivityKeywordItem';
import { IMyKeyword } from 'types/keyword';
import { getMyKeywordApi } from 'utils/apis/keyword';
import { MyActivityChartContainer } from './style';

function MyActivityChart() {
	const [myTopKeyword, setMyTopKeyword] = useState<IMyKeyword[]>([]);

	const searchMyKeyword = async () => {
		try {
			const memberkey = localStorage.getItem('memberkey');
			if (memberkey) {
				const response = await getMyKeywordApi(memberkey);
				setMyTopKeyword(response.data.data);
			}
		} catch (e) {
			console.log(e);
		}
	};

	useEffect(() => {
		searchMyKeyword();
	}, []);
	return (
		<MyActivityChartContainer>
			<div className="chart-title-text">
				<h3 className="chart-gray-color">내가 본 기사에서는&nbsp;</h3>
				<h3 className="chart-sub-color">이런 키워드가 많아요!</h3>
			</div>
			<div className="chart-keyword-rank">
				<MyActivityKeyword myTopKeyword={myTopKeyword} />
			</div>
			<div className="chart-graph-box">
				<MyActivityChartGraph myTopKeyword={myTopKeyword} />
			</div>
		</MyActivityChartContainer>
	);
}

export default MyActivityChart;
