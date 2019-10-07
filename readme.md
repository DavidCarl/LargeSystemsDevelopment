<h1>Hosting a Maven repository on github</h1>

```
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>davidcarl</username>
      <password>here</password>
    </server>
  </servers>
</settings>
```


<p>
https://stackoverflow.com/questions/14013644/hosting-a-maven-repository-on-github/14013645?fbclid=IwAR2wNR2b6bj5466EoCvYus1AQKFkqJpIX4Fp766A99_ZBGisBg7iQW8quHY#14013645
</p>




<div class="post-layout">
        <div class="votecell post-layout--left">
            

<div class="js-voting-container grid fd-column ai-stretch gs4 fc-black-200" data-post-id="14013645">
        <button class="js-vote-up-btn grid--cell s-btn s-btn__unset c-pointer" title="This answer is useful" aria-pressed="false" aria-label="up vote" data-selected-classes="fc-theme-primary"><svg aria-hidden="true" class="svg-icon m0 iconArrowUpLg" width="36" height="36" viewBox="0 0 36 36"><path d="M2 26h32L18 10 2 26z"></path></svg></button>
        <div class="js-vote-count grid--cell fc-black-500 fs-title grid fd-column ai-center" itemprop="upvoteCount" data-value="469">469</div>
        <button class="js-vote-down-btn grid--cell s-btn s-btn__unset c-pointer" title="This answer is not useful" aria-pressed="false" aria-label="down vote" data-selected-classes="fc-theme-primary"><svg aria-hidden="true" class="svg-icon m0 iconArrowDownLg" width="36" height="36" viewBox="0 0 36 36"><path d="M2 10h32L18 26 2 10z"></path></svg></button>


            <div class="js-accepted-answer-indicator grid--item fc-green-500 ta-center p4" title="loading when this answer was accepted..." tabindex="0" role="note" aria-label="accepted">
                <svg aria-hidden="true" class="svg-icon iconCheckmarkLg" width="36" height="36" viewBox="0 0 36 36"><path d="M6 14l8 8L30 6v8L14 30l-8-8v-8z"></path></svg>
            </div>

</div>

        </div>

        


<div class="answercell post-layout--right">
    
    <div class="post-text" itemprop="text">
<p>The best solution I've been able to find consists of these steps:</p>

<ol>
<li>Create a branch called <code>mvn-repo</code> to host your maven artifacts.</li>
<li>Use the github <a href="https://github.com/github/maven-plugins#readme" rel="noreferrer">site-maven-plugin</a> to push your artifacts to github.</li>
<li>Configure maven to use your remote <code>mvn-repo</code> as a maven repository.</li>
</ol>

<p>There are several benefits to using this approach:</p>

<ul>
<li>Maven artifacts are kept separate from your source in a separate branch called <code>mvn-repo</code>, much like github pages are kept in a separate branch called <code>gh-pages</code> (if you use github pages)</li>
<li>Unlike some other proposed solutions, it doesn't conflict with your <code>gh-pages</code> if you're using them.</li>
<li>Ties in naturally with the deploy target so there are no new maven commands to learn.  Just use <code>mvn deploy</code> as you normally would</li>
</ul>

<p>The typical way you deploy artifacts to a remote maven repo is to use <code>mvn deploy</code>, so let's patch into that mechanism for this solution.</p>

<p>First, tell maven to deploy artifacts to a temporary staging location inside your target directory.  Add this to your <code>pom.xml</code>:</p>

<pre class="lang-xml prettyprint prettyprinted" style=""><code><span class="tag">&lt;distributionManagement&gt;</span><span class="pln">
    </span><span class="tag">&lt;repository&gt;</span><span class="pln">
        </span><span class="tag">&lt;id&gt;</span><span class="pln">internal.repo</span><span class="tag">&lt;/id&gt;</span><span class="pln">
        </span><span class="tag">&lt;name&gt;</span><span class="pln">Temporary Staging Repository</span><span class="tag">&lt;/name&gt;</span><span class="pln">
        </span><span class="tag">&lt;url&gt;</span><span class="pln">file://${project.build.directory}/mvn-repo</span><span class="tag">&lt;/url&gt;</span><span class="pln">
    </span><span class="tag">&lt;/repository&gt;</span><span class="pln">
</span><span class="tag">&lt;/distributionManagement&gt;</span><span class="pln">

</span><span class="tag">&lt;plugins&gt;</span><span class="pln">
    </span><span class="tag">&lt;plugin&gt;</span><span class="pln">
        </span><span class="tag">&lt;artifactId&gt;</span><span class="pln">maven-deploy-plugin</span><span class="tag">&lt;/artifactId&gt;</span><span class="pln">
        </span><span class="tag">&lt;version&gt;</span><span class="pln">2.8.1</span><span class="tag">&lt;/version&gt;</span><span class="pln">
        </span><span class="tag">&lt;configuration&gt;</span><span class="pln">
            </span><span class="tag">&lt;altDeploymentRepository&gt;</span><span class="pln">internal.repo::default::file://${project.build.directory}/mvn-repo</span><span class="tag">&lt;/altDeploymentRepository&gt;</span><span class="pln">
        </span><span class="tag">&lt;/configuration&gt;</span><span class="pln">
    </span><span class="tag">&lt;/plugin&gt;</span><span class="pln">
</span><span class="tag">&lt;/plugins&gt;</span></code></pre>

<p>Now try running <code>mvn clean deploy</code>.  You'll see that it deployed your maven repository to <code>target/mvn-repo</code>.  The next step is to get it to upload that directory to GitHub.</p>

<p>Add your authentication information to <code>~/.m2/settings.xml</code> so that the github <code>site-maven-plugin</code> can push to GitHub:</p>

<pre class="lang-xml prettyprint prettyprinted" style=""><code><span class="com">&lt;!-- NOTE: MAKE SURE THAT settings.xml IS NOT WORLD READABLE! --&gt;</span><span class="pln">
</span><span class="tag">&lt;settings&gt;</span><span class="pln">
  </span><span class="tag">&lt;servers&gt;</span><span class="pln">
    </span><span class="tag">&lt;server&gt;</span><span class="pln">
      </span><span class="tag">&lt;id&gt;</span><span class="pln">github</span><span class="tag">&lt;/id&gt;</span><span class="pln">
      </span><span class="tag">&lt;username&gt;</span><span class="pln">YOUR-USERNAME</span><span class="tag">&lt;/username&gt;</span><span class="pln">
      </span><span class="tag">&lt;password&gt;</span><span class="pln">YOUR-PASSWORD</span><span class="tag">&lt;/password&gt;</span><span class="pln">
    </span><span class="tag">&lt;/server&gt;</span><span class="pln">
  </span><span class="tag">&lt;/servers&gt;</span><span class="pln">
</span><span class="tag">&lt;/settings&gt;</span></code></pre>

<p>(As noted, please make sure to <code>chmod 700 settings.xml</code> to ensure no one can read your password in the file.  If someone knows how to make site-maven-plugin prompt for a password instead of requiring it in a config file, let me know.)</p>

<p>Then tell the GitHub <code>site-maven-plugin</code> about the new server you just configured by adding the following to your pom:</p>

<pre class="lang-xml prettyprint prettyprinted" style=""><code><span class="tag">&lt;properties&gt;</span><span class="pln">
    </span><span class="com">&lt;!-- github server corresponds to entry in ~/.m2/settings.xml --&gt;</span><span class="pln">
    </span><span class="tag">&lt;github.global.server&gt;</span><span class="pln">github</span><span class="tag">&lt;/github.global.server&gt;</span><span class="pln">
</span><span class="tag">&lt;/properties&gt;</span></code></pre>

<p>Finally, configure the <code>site-maven-plugin</code> to upload from your temporary staging repo to your <code>mvn-repo</code> branch on Github:</p>

<pre class="lang-xml prettyprint prettyprinted" style=""><code><span class="tag">&lt;build&gt;</span><span class="pln">
    </span><span class="tag">&lt;plugins&gt;</span><span class="pln">
        </span><span class="tag">&lt;plugin&gt;</span><span class="pln">
            </span><span class="tag">&lt;groupId&gt;</span><span class="pln">com.github.github</span><span class="tag">&lt;/groupId&gt;</span><span class="pln">
            </span><span class="tag">&lt;artifactId&gt;</span><span class="pln">site-maven-plugin</span><span class="tag">&lt;/artifactId&gt;</span><span class="pln">
            </span><span class="tag">&lt;version&gt;</span><span class="pln">0.11</span><span class="tag">&lt;/version&gt;</span><span class="pln">
            </span><span class="tag">&lt;configuration&gt;</span><span class="pln">
                </span><span class="tag">&lt;message&gt;</span><span class="pln">Maven artifacts for ${project.version}</span><span class="tag">&lt;/message&gt;</span><span class="pln">  </span><span class="com">&lt;!-- git commit message --&gt;</span><span class="pln">
                </span><span class="tag">&lt;noJekyll&gt;</span><span class="pln">true</span><span class="tag">&lt;/noJekyll&gt;</span><span class="pln">                                  </span><span class="com">&lt;!-- disable webpage processing --&gt;</span><span class="pln">
                </span><span class="tag">&lt;outputDirectory&gt;</span><span class="pln">${project.build.directory}/mvn-repo</span><span class="tag">&lt;/outputDirectory&gt;</span><span class="pln"> </span><span class="com">&lt;!-- matches distribution management repository url above --&gt;</span><span class="pln">
                </span><span class="tag">&lt;branch&gt;</span><span class="pln">refs/heads/mvn-repo</span><span class="tag">&lt;/branch&gt;</span><span class="pln">                       </span><span class="com">&lt;!-- remote branch name --&gt;</span><span class="pln">
                </span><span class="tag">&lt;includes&gt;&lt;include&gt;</span><span class="pln">**/*</span><span class="tag">&lt;/include&gt;&lt;/includes&gt;</span><span class="pln">
                </span><span class="tag">&lt;repositoryName&gt;</span><span class="pln">YOUR-REPOSITORY-NAME</span><span class="tag">&lt;/repositoryName&gt;</span><span class="pln">      </span><span class="com">&lt;!-- github repo name --&gt;</span><span class="pln">
                </span><span class="tag">&lt;repositoryOwner&gt;</span><span class="pln">YOUR-GITHUB-USERNAME</span><span class="tag">&lt;/repositoryOwner&gt;</span><span class="pln">    </span><span class="com">&lt;!-- github username  --&gt;</span><span class="pln">
            </span><span class="tag">&lt;/configuration&gt;</span><span class="pln">
            </span><span class="tag">&lt;executions&gt;</span><span class="pln">
              </span><span class="com">&lt;!-- run site-maven-plugin's 'site' target as part of the build's normal 'deploy' phase --&gt;</span><span class="pln">
              </span><span class="tag">&lt;execution&gt;</span><span class="pln">
                </span><span class="tag">&lt;goals&gt;</span><span class="pln">
                  </span><span class="tag">&lt;goal&gt;</span><span class="pln">site</span><span class="tag">&lt;/goal&gt;</span><span class="pln">
                </span><span class="tag">&lt;/goals&gt;</span><span class="pln">
                </span><span class="tag">&lt;phase&gt;</span><span class="pln">deploy</span><span class="tag">&lt;/phase&gt;</span><span class="pln">
              </span><span class="tag">&lt;/execution&gt;</span><span class="pln">
            </span><span class="tag">&lt;/executions&gt;</span><span class="pln">
        </span><span class="tag">&lt;/plugin&gt;</span><span class="pln">
    </span><span class="tag">&lt;/plugins&gt;</span><span class="pln">
</span><span class="tag">&lt;/build&gt;</span></code></pre>

<p>The <code>mvn-repo</code> branch does not need to exist, it will be created for you.</p>

<p>Now run <code>mvn clean deploy</code> again.  You should see maven-deploy-plugin "upload" the files to your local staging repository in the target directory, then site-maven-plugin committing those files and pushing them to the server.</p>

<pre class="lang-xml prettyprint prettyprinted" style=""><code><span class="pln">[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building DaoCore 1.3-SNAPSHOT
[INFO] ------------------------------------------------------------------------
...
[INFO] --- maven-deploy-plugin:2.5:deploy (default-deploy) @ greendao ---
Uploaded: file:///Users/mike/Projects/greendao-emmby/DaoCore/target/mvn-repo/com/greendao-orm/greendao/1.3-SNAPSHOT/greendao-1.3-20121223.182256-3.jar (77 KB at 2936.9 KB/sec)
Uploaded: file:///Users/mike/Projects/greendao-emmby/DaoCore/target/mvn-repo/com/greendao-orm/greendao/1.3-SNAPSHOT/greendao-1.3-20121223.182256-3.pom (3 KB at 1402.3 KB/sec)
Uploaded: file:///Users/mike/Projects/greendao-emmby/DaoCore/target/mvn-repo/com/greendao-orm/greendao/1.3-SNAPSHOT/maven-metadata.xml (768 B at 150.0 KB/sec)
Uploaded: file:///Users/mike/Projects/greendao-emmby/DaoCore/target/mvn-repo/com/greendao-orm/greendao/maven-metadata.xml (282 B at 91.8 KB/sec)
[INFO] 
[INFO] --- site-maven-plugin:0.7:site (default) @ greendao ---
[INFO] Creating 24 blobs
[INFO] Creating tree with 25 blob entries
[INFO] Creating commit with SHA-1: 0b8444e487a8acf9caabe7ec18a4e9cff4964809
[INFO] Updating reference refs/heads/mvn-repo from ab7afb9a228bf33d9e04db39d178f96a7a225593 to 0b8444e487a8acf9caabe7ec18a4e9cff4964809
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 8.595s
[INFO] Finished at: Sun Dec 23 11:23:03 MST 2012
[INFO] Final Memory: 9M/81M
[INFO] ------------------------------------------------------------------------</span></code></pre>

<p>Visit github.com in your browser, select the <code>mvn-repo</code> branch, and verify that all your binaries are now there.</p>

<p><img src="https://i.stack.imgur.com/RQUz0.png" alt="enter image description here"></p>

<p><strong>Congratulations!</strong></p>

<p>You can now deploy your maven artifacts to a poor man's public repo simply by running <code>mvn clean deploy</code>.</p>

<p>There's one more step you'll want to take, which is to configure any poms that depend on your pom to know where your repository is.  Add the following snippet to any project's pom that depends on your project:</p>

<pre class="lang-xml prettyprint prettyprinted" style=""><code><span class="tag">&lt;repositories&gt;</span><span class="pln">
    </span><span class="tag">&lt;repository&gt;</span><span class="pln">
        </span><span class="tag">&lt;id&gt;</span><span class="pln">YOUR-PROJECT-NAME-mvn-repo</span><span class="tag">&lt;/id&gt;</span><span class="pln">
        </span><span class="tag">&lt;url&gt;</span><span class="pln">https://raw.github.com/YOUR-USERNAME/YOUR-PROJECT-NAME/mvn-repo/</span><span class="tag">&lt;/url&gt;</span><span class="pln">
        </span><span class="tag">&lt;snapshots&gt;</span><span class="pln">
            </span><span class="tag">&lt;enabled&gt;</span><span class="pln">true</span><span class="tag">&lt;/enabled&gt;</span><span class="pln">
            </span><span class="tag">&lt;updatePolicy&gt;</span><span class="pln">always</span><span class="tag">&lt;/updatePolicy&gt;</span><span class="pln">
        </span><span class="tag">&lt;/snapshots&gt;</span><span class="pln">
    </span><span class="tag">&lt;/repository&gt;</span><span class="pln">
</span><span class="tag">&lt;/repositories&gt;</span></code></pre>

<p>Now any project that requires your jar files will automatically download them from your github maven repository.</p>

<p>Edit: to avoid the problem mentioned in the comments ('Error creating commit: Invalid request. For 'properties/name', nil is not a string.'), make sure you state a name in your profile on github.</p>
    </div>
    <div class="grid mb0 fw-wrap ai-start jc-end gs8 gsy">
        <time itemprop="dateCreated" datetime="2012-12-23T18:43:35"></time>
        <div class="grid--cell mr16" style="flex: 1 1 100px;">
            <div class="post-menu grid ai-baseline sm:fd-column-reverse"><div class="post-menu-container grid--cell"><a href="/a/14013645" itemprop="url" class="js-share-link" title="short permalink to this answer" data-controller="se-share-sheet s-popover" data-se-share-sheet-title="Share a link to this answer" data-se-share-sheet-subtitle="" data-se-share-sheet-post-type="answer" data-se-share-sheet-social="facebook twitter devto" data-se-share-sheet-location="2" data-s-popover-placement="bottom-start" aria-controls="se-share-sheet-1" data-action=" s-popover#toggle se-share-sheet#preventNavigation s-popover:show->se-share-sheet#willShow s-popover:shown->se-share-sheet#didShow">share</a><div class="s-popover z-dropdown" style="width: unset; max-width: 28em; position: absolute; transform: translate3d(244px, 3904px, 0px); top: 0px; left: 0px; will-change: transform;" id="se-share-sheet-1" x-placement="top-start"><div class="s-popover--arrow"></div><div><span class="js-title fw-bold">Share a link to this answer</span> <span class="js-subtitle"></span></div><div class="my8"><input type="text" class="js-input s-input wmn3 sm:wmn-initial" readonly=""></div><div class="d-flex jc-space-between mbn4"><button class="js-copy-link-btn s-btn s-btn__link">Copy link</button><div class="js-social-container"></div></div></div><span class="lsep">|</span><a href="/posts/14013645/edit" class="suggest-edit-post" title="">improve this answer</a></div></div>
        </div>
        <div class="post-signature grid--cell fl0">
<div class="user-info ">
    <div class="user-action-time">
        <a href="/posts/14013645/revisions" title="show all edits to this post">edited <span title="2016-06-13 14:49:03Z" class="relativetime">Jun 13 '16 at 14:49</span></a>
    </div>
    <div class="user-gravatar32">
        <a href="/users/6439655/fkleedorfer"><div class="gravatar-wrapper-32"><img src="https://i.stack.imgur.com/5WJBn.png?s=32&amp;g=1" alt="" width="32" height="32"></div></a>
    </div>
    <div class="user-details">
        <a href="/users/6439655/fkleedorfer">fkleedorfer</a>
        <div class="-flair">
            <span class="reputation-score" title="reputation score " dir="ltr">3</span><span title="1 bronze badge" aria-hidden="true"><span class="badge3"></span><span class="badgecount">1</span></span><span class="v-visible-sr">1 bronze badge</span>
        </div>
    </div>
</div>    </div>



    <div class="post-signature owner grid--cell fl0">
<div class="user-info user-hover">
    <div class="user-action-time">
        answered <span title="2012-12-23 18:43:35Z" class="relativetime">Dec 23 '12 at 18:43</span>
    </div>
    <div class="user-gravatar32">
        <a href="/users/82156/emmby"><div class="gravatar-wrapper-32"><img src="https://www.gravatar.com/avatar/5b800d2444930f6f465fb284647b5204?s=32&amp;d=identicon&amp;r=PG" alt="" width="32" height="32"></div></a>
    </div>
    <div class="user-details" itemprop="author" itemscope="" itemtype="http://schema.org/Person">
        <a href="/users/82156/emmby">emmby</a><span class="d-none" itemprop="name">emmby</span>
        <div class="-flair">
            <span class="reputation-score" title="reputation score 67,605" dir="ltr">67.6k</span><span title="58 gold badges" aria-hidden="true"><span class="badge1"></span><span class="badgecount">58</span></span><span class="v-visible-sr">58 gold badges</span><span title="174 silver badges" aria-hidden="true"><span class="badge2"></span><span class="badgecount">174</span></span><span class="v-visible-sr">174 silver badges</span><span title="237 bronze badges" aria-hidden="true"><span class="badge3"></span><span class="badgecount">237</span></span><span class="v-visible-sr">237 bronze badges</span>
        </div>
    </div>
</div>

    </div>
    </div>
    
</div>

        <div class="js-post-notices post-layout--full">
        </div>

    <div class="post-layout--right">
        <div id="comments-14013645" class="comments js-comments-container  bt bc-black-2 mt12" data-post-id="14013645" data-min-length="15">
            <ul class="comments-list js-comments-list" data-remaining-comments-count="26" data-canpost="false" data-cansee="true" data-comments-unavailable="false" data-addlink-disabled="true">


    <li id="comment-19353006" class="comment js-comment " data-comment-id="19353006">
        <div class="js-comment-actions comment-actions">
            <div class="comment-score js-comment-edit-hide">
                    <span title="number of 'useful comment' votes received" class="hot">25</span>
            </div>
                    </div>
        <div class="comment-text js-comment-text-and-form">
            <div class="comment-body js-comment-edit-hide">
                
                <span class="comment-copy">Note also that this solution will overwrite your previous artifacts every time you deploy.  This is appropriate for snapshot repositories, but not for released artifacts.  To disable that behavior, set <code>&lt;merge&gt;true&lt;/merge&gt;</code> in your site-maven-plugin configuration.  If you do that, though, I think you'll have to manually create the mvn-repo branch in github and delete all its files the first time around.</span>
                
                    –&nbsp;<a href="/users/82156/emmby" title="67,605 reputation" class="comment-user owner">emmby</a>
                <span class="comment-date" dir="ltr"><a class="comment-link" href="#comment19353006_14013645"><span title="2012-12-23 21:47:26Z" class="relativetime-clean">Dec 23 '12 at 21:47</span></a></span>
                        <span title="this comment was edited 1 time">
                            <svg aria-hidden="true" class="svg-icon va-text-bottom o50 iconPencilSm" width="14" height="14" viewBox="0 0 14 14"><path d="M11.1 1.71l1.13 1.12c.2.2.2.51 0 .71L11.1 4.7 9.21 2.86l1.17-1.15c.2-.2.51-.2.71 0zM2 10.12l6.37-6.43 1.88 1.88L3.88 12H2v-1.88z"></path></svg>
                        </span>
                                                            </div>
        </div>
    </li>
    <li id="comment-19354193" class="comment js-comment " data-comment-id="19354193">
        <div class="js-comment-actions comment-actions">
            <div class="comment-score js-comment-edit-hide">
                    <span title="number of 'useful comment' votes received" class="warm">13</span>
            </div>
                    </div>
        <div class="comment-text js-comment-text-and-form">
            <div class="comment-body js-comment-edit-hide">
                
                <span class="comment-copy">+1 clever and well presented. My only criticism is that you did not include a link to the Maven plugins site: <a href="https://github.com/github/maven-plugins" rel="nofollow noreferrer">github.com/github/maven-plugins</a>. Thx I was looking for a way to publish my Maven site to github!</span>
                
                    –&nbsp;<a href="/users/256618/mark-oconnor" title="68,141 reputation" class="comment-user">Mark O'Connor</a>
                <span class="comment-date" dir="ltr"><a class="comment-link" href="#comment19354193_14013645"><span title="2012-12-23 23:46:45Z" class="relativetime-clean">Dec 23 '12 at 23:46</span></a></span>
                        <span title="this comment was edited 2 times">
                            <svg aria-hidden="true" class="svg-icon va-text-bottom o50 iconPencilSm" width="14" height="14" viewBox="0 0 14 14"><path d="M11.1 1.71l1.13 1.12c.2.2.2.51 0 .71L11.1 4.7 9.21 2.86l1.17-1.15c.2-.2.51-.2.71 0zM2 10.12l6.37-6.43 1.88 1.88L3.88 12H2v-1.88z"></path></svg>
                        </span>
                                                            </div>
        </div>
    </li>
    <li id="comment-31008990" class="comment js-comment " data-comment-id="31008990">
        <div class="js-comment-actions comment-actions">
            <div class="comment-score js-comment-edit-hide">
                    <span title="number of 'useful comment' votes received" class="warm">7</span>
            </div>
                    </div>
        <div class="comment-text js-comment-text-and-form">
            <div class="comment-body js-comment-edit-hide">
                
                <span class="comment-copy">This approach does not work when Two-Factor authentication is used on github. See my note in the issue here: <a href="https://github.com/github/maven-plugins/issues/36#issuecomment-31005606" rel="nofollow noreferrer">github.com/github/maven-plugins/issues/36#issuecomment-31005606</a></span>
                
                    –&nbsp;<a href="/users/363281/dag" title="5,656 reputation" class="comment-user">Dag</a>
                <span class="comment-date" dir="ltr"><a class="comment-link" href="#comment31008990_14013645"><span title="2013-12-20 12:09:24Z" class="relativetime-clean">Dec 20 '13 at 12:09</span></a></span>
                                                            </div>
        </div>
    </li>
    <li id="comment-33380682" class="comment js-comment " data-comment-id="33380682">
        <div class="js-comment-actions comment-actions">
            <div class="comment-score js-comment-edit-hide">
                    <span title="number of 'useful comment' votes received" class="hot">17</span>
            </div>
                    </div>
        <div class="comment-text js-comment-text-and-form">
            <div class="comment-body js-comment-edit-hide">
                
                <span class="comment-copy">In order to make this work for <b>multi-module projects</b>, you can also simply use <code>&lt;altDeploymentRepository&gt;internal.repo::default::file://${user.dir}/target/mvn-repo&lt;/altDeploymentRepository&gt;</code> with the <i>maven-deploy-plugin</i>, and <code>&lt;outputDirectory&gt;${user.dir}/target/mvn-repo&lt;/outputDirectory&gt;</code> with <i>site-maven-plugin</i>. This will deploy all artifacts into the root ("parent") project, and push them to the respective parent direcory on github.  Otherwise, the build of each sub-module will overwrite that of the sub-module built before...</span>
                
                    –&nbsp;<a href="/users/731040/s-d" title="2,457 reputation" class="comment-user">s.d</a>
                <span class="comment-date" dir="ltr"><a class="comment-link" href="#comment33380682_14013645"><span title="2014-02-25 16:58:28Z" class="relativetime-clean">Feb 25 '14 at 16:58</span></a></span>
                        <span title="this comment was edited 1 time">
                            <svg aria-hidden="true" class="svg-icon va-text-bottom o50 iconPencilSm" width="14" height="14" viewBox="0 0 14 14"><path d="M11.1 1.71l1.13 1.12c.2.2.2.51 0 .71L11.1 4.7 9.21 2.86l1.17-1.15c.2-.2.51-.2.71 0zM2 10.12l6.37-6.43 1.88 1.88L3.88 12H2v-1.88z"></path></svg>
                        </span>
                                                            </div>
        </div>
    </li>
    <li id="comment-45292819" class="comment js-comment " data-comment-id="45292819">
        <div class="js-comment-actions comment-actions">
            <div class="comment-score js-comment-edit-hide">
                    <span title="number of 'useful comment' votes received" class="warm">7</span>
            </div>
                    </div>
        <div class="comment-text js-comment-text-and-form">
            <div class="comment-body js-comment-edit-hide">
                
                <span class="comment-copy">Two suggestions that make it work (at least for me): Set current version of Github plugin (right now it would be 0.11). Also I would suggest everybody to use a OAUTH token instead of the password. You can generate it in 'Settings-&gt;Applications-&gt;Personal Access Tokens'. Than you also can inline it into the POM via and store the token as environment variable.  <code>&lt;github.global.userName&gt;YourUserName&lt;/github.global.userName&gt;         &lt;github.global.password&gt;${GITHUB_OAUTH_TOKEN&lt;/github.global.password&gt;</code></span>
                
                    –&nbsp;<a href="/users/1339560/florian-loch" title="728 reputation" class="comment-user">Florian Loch</a>
                <span class="comment-date" dir="ltr"><a class="comment-link" href="#comment45292819_14013645"><span title="2015-02-12 18:55:19Z" class="relativetime-clean">Feb 12 '15 at 18:55</span></a></span>
                        <span title="this comment was edited 3 times">
                            <svg aria-hidden="true" class="svg-icon va-text-bottom o50 iconPencilSm" width="14" height="14" viewBox="0 0 14 14"><path d="M11.1 1.71l1.13 1.12c.2.2.2.51 0 .71L11.1 4.7 9.21 2.86l1.17-1.15c.2-.2.51-.2.71 0zM2 10.12l6.37-6.43 1.88 1.88L3.88 12H2v-1.88z"></path></svg>
                        </span>
                                                            </div>
        </div>
    </li>
            </ul>
	    </div>

        <div id="comments-link-14013645" data-rep="50" data-anon="true">

                    <a class="js-add-link comments-link dno" title="Use comments to ask for more information or suggest improvements. Avoid comments like “+1” or “thanks”."></a>
                <span class="js-link-separator dno">&nbsp;|&nbsp;</span>
            <a class="js-show-link comments-link " title="expand to show all comments on this post" href="#" onclick="">show <b>26</b> more comments</a>
        </div>         
    </div>    </div>
