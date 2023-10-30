import styled, { css } from 'styled-components';

const colorSet = {
	Primary: css`
		color: var(--main-color);
	`,
	SubFirst: css`
		color: var(--sub-color-1);
	`,
	Black: css`
		color: var(--black-500);
	`,
};

interface IAreaTitleWrapperProps {
	$color: 'Primary' | 'SubFirst' | 'Black';
}

export const AreaTitleWrapper = styled.h2<IAreaTitleWrapperProps>`
	display: flex;
	flex-direction: row;
	gap: 0.5rem;
	align-items: center;
	font-size: 1.5rem;
	font-weight: bold;
	padding: 1rem 0;

	${({ $color }) => colorSet[$color]};

	.subStr {
		display: flex;
		gap: 5px;
		align-items: center;
		color: var(--gray-300);

		.tooltip {
			display: flex;
			svg {
				fill: var(--gray-300);

				&:before {
					display: none;
				}
			}

			&:hover {
				cursor: pointer;
				&:before {
					position: absolute;
					padding: 10px;
					background-color: var(--sub-color-1);
					color: var(--white-color);
					border-radius: var(--radius-s);
					transform: translate(-40%, -110%);
					font-size: 0.8rem;
					display: block;
					content: '매 정시에 업데이트 됩니다';
				}
			}
		}
	}
`;
