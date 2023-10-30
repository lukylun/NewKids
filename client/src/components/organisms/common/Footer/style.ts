import styled from 'styled-components';

export const FooterConatiner = styled.div`
	border-radius: var(--radius-m) var(--radius-m) 0 0;
	display: flex;
	justify-content: center;
	height: 10rem;
	background-color: var(--footer-bg-color);

	.wrapper {
		width: var(--content-width-l);
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: space-between;

		.content {
			display: flex;
			flex-direction: column;
			gap: 8px;

			.links {
				button {
					padding: 0 10px 0 0;
				}
			}

			.team {
				margin-bottom: 1rem;
			}

			.copyright {
				font-weight: bold;
				display: flex;
				align-items: center;
			}
		}

		img {
			width: 9rem;
			height: 3rem;
		}
	}
`;
