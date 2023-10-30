import styled from 'styled-components';

export const SquareArticleListItemContainer = styled.div`
	position: relative;

	img {
		width: 100%;
		aspect-ratio: 1/1;
		border-radius: var(--radius-m);
	}

	.overlay {
		border-radius: var(--radius-m);
		position: absolute;
		opacity: 1;
		background: linear-gradient(transparent 50%, rgba(0, 0, 0, 0.9) 100%);
		z-index: 10;
		top: 0;
		width: 100%;
		height: 100%;
	}

	&:hover {
		cursor: pointer;

		.overlay {
			background: linear-gradient(transparent 20%, rgba(0, 0, 0, 0.9) 100%);
			/* background: linear-gradient(transparent 20%, rgba(255, 119, 56, 0.9) 100%); */
		}
	}
`;
