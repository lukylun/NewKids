import styled from 'styled-components';

export const IndexPageLayoutContainer = styled.div`
	margin-top: 3rem;

	& > div:nth-child(1) {
		position: relative;
		display: flex;
		flex-direction: column;
		gap: 5rem;
		min-height: 100vh;

		.popular-article-list {
			position: relative;
		}

		.trending-keyword-list {
			position: relative;
		}

		.recommended-article-list {
			position: relative;
		}

		.word-cloud {
			margin-bottom: 10rem;
			position: relative;
		}
	}
`;
