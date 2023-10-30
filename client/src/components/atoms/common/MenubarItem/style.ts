import styled from 'styled-components';

export const MenubarItemContainer = styled.div`
	width: 6rem;
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 5px;

	&:hover {
		cursor: pointer;
	}

	.icon {
		svg {
			width: 40px;
			height: 40px;
		}
	}

	.active {
		color: var(--main-color);
		svg {
			fill: var(--main-color);
		}
	}
	.name {
	}
`;
