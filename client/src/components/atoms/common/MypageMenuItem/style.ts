import styled from 'styled-components';

export const MypageMenuItemContainer = styled.div`
	width: 5rem;
	margin: 0.5rem;
	text-align: center;

	&:hover {
		cursor: pointer;
	}

	.icon {
		font-size: 20px;
		svg {
			width: 40px;
			height: 40px;
		}
	}
	.icon-active {
		font-size: 20px;
		color: var(--main-color);
		svg {
			width: 40px;
			height: 40px;
			fill: var(--main-color);
		}
	}
`;
