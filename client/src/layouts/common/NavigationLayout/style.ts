import styled from 'styled-components';

export const NavigationLayoutWrapper = styled.div`
	box-shadow: 0 3px 30px 0 rgba(0, 0, 0, 0.03);
	position: fixed;
	width: 100%;
	background-color: var(--white-color);
	z-index: 50;

	.navigation-container {
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
		.navibar {
			height: 76px;
		}

		.menubar {
			height: 90px;
		}
	}
`;
