repositories.remote << 'http://repo1.maven.org/maven2'
repositories.remote << 'http://www.ibiblio.org/maven2'

GUAVA=transitive('com.google.guava:guava:jar:14.0.1')

define 'bullet' do
	project.version = '0.0.0'
	compile.using(:lint => 'all')
	package(:jar)
	test.using(:junit)
end
