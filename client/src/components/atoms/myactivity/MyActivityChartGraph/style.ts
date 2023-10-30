import styled from 'styled-components';

export const MyActivityChartGraphWrapper = styled.div`
	margin-top: 1rem;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;

	.chart-graph {
		width: 80%;
		height: 300px;
		canvas {
			width: 90%;
		}
	}
`;
