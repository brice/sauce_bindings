from simplesauce.options import SauceOptions


class TestEdge(object):

    def test_defaults(self):
        sauce = SauceOptions('edge')

        assert sauce.browserName == 'edge'
        assert sauce.browserVersion == 'latest'
        assert sauce.platformName == 'windows 10'
