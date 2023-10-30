import styled from 'styled-components';

export const WordCloudContainer = styled.div`
	background-color: var(--gray-100);
	border-radius: var(--radius-m);

	.tippy-box[data-theme~='test'] {
		background-color: blue;
		color: yellow;
	}
`;
