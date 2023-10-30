import styled from 'styled-components';

export const MyActivityChartContainer = styled.div`
	width: 100%;
	height: 100%;
	margin-top: 1.5rem;

	.chart-title-text {
		display: flex;
		font-weight: bold;

		> .chart-sub-color {
			color: var(--sub-color-1);
			font-size: 24px;
		}

		> .chart-gray-color {
			color: var(--gray-500);
			font-size: 24px;
		}
	}

	.chart-graph-box {
		width: 100%;
		height: 100%;
		canvas {
			background-color: var(--gray-100);
		}
	}
`;
