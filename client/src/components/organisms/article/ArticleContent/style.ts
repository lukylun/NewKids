import styled from 'styled-components';

export const ArticleContentContainer = styled.div`
	padding-bottom: 5rem;
	position: relative;
	min-height: calc(100vh);
	display: flex;
	justify-content: space-between;
	gap: 10px;

	&::after {
		content: '';
		position: absolute;
		bottom: 0;
		width: 100%;
		height: 2px;
		background-color: var(--main-color);
	}
	& > div:nth-child(1) {
		margin: 0;
	}

	.article-content * {
		display: flex;
		flex-direction: column;
		gap: 1rem;
		background-color: #fff !important;
		max-width: var(--content-width-full);
		line-height: 1.7;
		font-size: 1rem;

		img {
			width: 100%;
			height: auto;
			display: block;
			margin: 0 auto;
		}
	}
`;
