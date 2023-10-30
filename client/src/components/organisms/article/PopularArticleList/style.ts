import styled from 'styled-components';

export const PopularArticleListContainer = styled.div`
	position: relative;
	display: grid;
	gap: 1rem;
	grid-template-columns: 2fr 1fr 1fr;

	/* PopularArticleListItem */
	div {
		img {
			height: 240px;
		}

		h2 {
			color: white;
			bottom: 0;
			position: absolute;
			z-index: 11;
			font-size: 1.5rem;
			margin: 1rem;
			white-space: normal;
			display: -webkit-box;
			-webkit-line-clamp: 2;
			-webkit-box-orient: vertical;
			overflow: hidden;
			transition: all 0.1s;
		}

		&:hover {
			h2 {
				transform: translateY(-20%);
			}
		}
	}

	div:nth-child(1) {
		grid-column: 1 / 2;
		grid-row: 1 / 3;

		img {
			height: 500px;
		}

		h2 {
			font-size: 3rem;
		}
	}
`;
