import styled from 'styled-components';

export const NavBarContainer = styled.div<{ $auth?: boolean }>`
	z-index: 20;
	position: relative;
	background-color: var(--white-color);
	height: 100%;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: ${({ $auth }) => ($auth ? 'center' : 'space-between')};

	.logo {
		display: flex;
		align-items: center;

		svg {
			width: 170px;
			height: 56px;
		}

		&:hover {
			cursor: pointer;
		}
	}

	.search-bar {
	}

	.member-info {
		padding: 1rem 0;
		color: var(--main-color);
		display: flex;
		flex-direction: row;
		align-items: center;
		transition: all 0.2s;

		span {
			cursor: pointer;
		}

		svg {
			fill: var(--sub-color-1);
		}

		.menu {
			box-shadow: 0 4px 4px -4px rgba(0, 0, 0, 0.3);
			display: none;
			flex-direction: column;
			align-items: center;
			padding: 1rem;
			gap: 1rem;
			width: 150px;
			position: absolute;
			top: 3rem;
			transform: translateX(-30%);
			border-radius: var(--radius-s);
			border: 1px solid var(--gray-200);
		}

		.menu > * {
			width: fit-content;
			color: var(--gray-500);
			font-family: 'Pretendard';
			font-size: 1rem;
			text-align: center;
			font-weight: 500;
		}

		&:hover {
			.menu {
				display: flex;
			}
		}
	}
`;
