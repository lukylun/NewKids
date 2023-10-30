import styled from 'styled-components';

export const FloatingElementsContainer = styled.div`
	@media screen and (min-width: 0) {
		display: none;
	}
	@media screen and (min-width: 1650px) {
		display: block;
	}

	z-index: 10;
	position: relative;
	transform: translateY(36%);

	.left-element {
		position: fixed;
		display: flex;
		flex-direction: column;
		gap: 7rem;
		top: 33vh;
		left: 2vw;

		// 첫 번째 구름
		div:nth-child(1) {
			transition: all 1s;
			animation: left-and-right 7s linear 1s infinite alternate;

			&:before {
				background-image: url('/assets/imgs/floating-icons.png');
				display: block;
				background-size: 388px 349px;
				background-position: -123px -268px;
				width: 35px;
				height: 21px;
				content: '';
			}
		}

		// 두 번째 구름
		div:nth-child(2) {
			transition: all 1s;
			animation: right-and-left 5s linear 0.5s infinite alternate;

			&:before {
				display: block;
				background-image: url('/assets/imgs/floating-icons.png');
				background-size: 388px 349px;
				background-position: -198px -128px;
				width: 62px;
				height: 37px;
				content: '';
			}
		}

		// 세번째 해
		div:nth-child(3) {
			transition: all 1s;
			animation: left-and-right 3s linear 1s infinite alternate;

			&:before {
				display: block;
				width: 36px;
				height: 36px;
				border-radius: 100%;
				background-color: #ffd82a;
				transition: all 2s;
				content: '';
			}
		}
	}

	.right-element {
		position: fixed;
		display: flex;
		flex-direction: column;
		gap: 7rem;
		top: 33vh;
		right: 2vw;

		// 세번째 해
		div:nth-child(1) {
			transition: all 1s;
			animation: right-and-left 5s linear 0.5s infinite alternate;

			&:before {
				display: block;
				width: 36px;
				height: 36px;
				border-radius: 100%;
				background-color: #fc615d;
				transition: all 2s;
				content: '';
			}
		}

		// 첫 번째 구름
		div:nth-child(2) {
			transition: all 1s;
			animation: left-and-right 3s linear 1s infinite alternate;

			&:before {
				background-image: url('/assets/imgs/floating-icons.png');
				display: block;
				background-size: 388px 349px;
				background-position: -123px -268px;
				transform: scale(1.5);
				width: 35px;
				height: 21px;
				content: '';
			}
		}

		// 두 번째 구름
		div:nth-child(3) {
			transition: all 1s;
			animation: left-and-right 7s linear 1s infinite alternate;

			&:before {
				display: block;
				background-image: url('/assets/imgs/floating-icons.png');
				transform: scale(1.5);
				background-size: 388px 349px;
				background-position: -198px -128px;
				width: 62px;
				height: 37px;
				content: '';
			}
		}
	}
`;
