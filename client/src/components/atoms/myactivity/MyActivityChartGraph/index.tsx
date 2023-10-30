import React from 'react';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from 'chart.js';
import { IMyKeyword } from 'types/keyword';
import { MyActivityChartGraphWrapper } from './style';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

interface IMyActivityChartGraphProps {
	myTopKeyword: IMyKeyword[];
}
function MyActivityChartGraph(props: IMyActivityChartGraphProps) {
	const { myTopKeyword } = props;
	const chartBarColors = [
		'rgb(249, 65, 68)',
		'rgb(248, 150, 30)',
		'rgb(249, 199, 79)',
		'rgb(144, 190, 119)',
		'rgb(56, 97, 156)',
	];
	const options = {
		responsive: true,
		maintainAspectRatio: false,
		plugins: {
			legend: {
				position: 'bottom' as const,
				labels: {
					generateLabels: (chart: ChartJS) => {
						const { data } = chart;
						const labels = data.labels || [];
						return labels.map((label, index) => ({
							text: label as string,
							fillStyle: chartBarColors[index],
							strokeStyle: 'white',
						}));
					},
					font: {
						size: 14,
						color: 'black',
						weight: 'bold',
					},
				},
			},
			title: {
				display: true,
				text: '키워드 통계',
				font: {
					size: 20,
				},
			},
		},
		scales: {
			x: {
				display: true,
				labels: ['', '', '', '', ''],
			},
			y: {
				display: true,
				ticks: {
					maxTicksLimit: 5,
					font: {
						size: 14,
					},
					color: 'black',
				},
			},
		},
		layout: {
			padding: {
				left: 10,
				right: 20,
				top: 20,
			},
		},
	};
	const labels = myTopKeyword.map((keyword) => keyword.keyword);
	const keywordCnt = myTopKeyword.map((keyword) => keyword.count);

	const data = {
		labels,
		datasets: [
			{
				data: keywordCnt,
				backgroundColor: chartBarColors,
			},
		],
	};
	return (
		<MyActivityChartGraphWrapper>
			<div className="chart-graph">
				<Bar options={options} data={data} style={{ position: 'relative', height: '300px' }} />
			</div>
		</MyActivityChartGraphWrapper>
	);
}

export default MyActivityChartGraph;
