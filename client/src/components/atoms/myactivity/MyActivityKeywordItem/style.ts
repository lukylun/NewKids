import styled from 'styled-components';

export const MyActivityKeywordWrapper = styled.div`
	margin-top: 1rem;

	.keyword-rank-box {
		display: flex;
		justify-content: center;
		gap: 20px;

		> .keyword-rank-text {
			display: flex;
			justify-content: center;
			align-items: center;
			gap: 10px;
			background-color: var(--gray-100);
			border-radius: var(--radius-s);
			width: 130px;
			height: 50px;

			> .rank-number {
				color: var(--sub-color-1);
				font-size: 16px;
			}

			> .rank-title {
				color: var(--gray-500);
				font-size: 16px;
			}
		}
	}
`;
