import styled from 'styled-components';

export const NarrowContentLayoutContainer = styled.div`
	width: var(--content-width-m);
	margin: 0 auto;
`;

export const ContentLayoutContainer = styled.div`
	margin: 0 auto;

	@media screen and (min-width: 0) {
		width: var(--content-width-full);
	}

	@media screen and (min-width: 1440px) {
		width: var(--content-width-l);
	}

	@media screen and (min-width: 1441px) {
		width: var(--content-width-xl);
	}
`;

export const FullContentLayoutContainer = styled.div`
	width: var(--content-width-full);
	margin: 0 auto;
`;
