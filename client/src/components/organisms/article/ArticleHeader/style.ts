import styled from 'styled-components';

export const ArticleHeaderContainer = styled.div`
	position: relative;
	display: flex;
	flex-direction: column;

	.title {
		font-size: 2rem;
		font-weight: bold;
	}
	.info {
		color: var(--gray-500);
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: end;

		.right {
			text-align: end;

			.view {
				display: flex;
				flex-direction: row;
				align-items: center;
				justify-content: flex-end;
				gap: 5px;

				svg {
					fill: var(--gray-500);
					width: 20px;
					height: 20px;
				}
			}
		}
	}

	&::after {
		content: '';
		margin: 1rem 0;
		width: 100%;
		height: 2px;
		background-color: var(--main-color);
	}
`;
