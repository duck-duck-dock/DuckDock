import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

//用于查词的弹出菜单
class popMenu extends JPopupMenu{
    JMenuItem search;
    public popMenu(){
        this.setBorderPainted(false);
        this.setBackground(Color.white);
        search=new JMenuItem("查询");
        this.add(search);
    }
}
public class PracticePage extends JPanel {
    JButton BackButton;
    JPanel articleMain,article1, article2, article3;//右边界面+三个文章界面
    JPanel leftPanel;//左侧按钮界面
    JButton first,second,third;//三套阅读的按钮
    JTextArea test1,test2,test3;
    CardLayout thisLayout;//卡片布局
    JScrollPane jp,jp2,jp3;//用来显示滚动文本框

    private void initText(){
        test1=new JTextArea("\n\n2020 年研究生入学统一考试试题（英语一）\n\n Section I Use of English Directions： Read the following text. Choose the best word (s) for each numbered blank and mark A, B, C or D on the ANSWER SHEET. (10 points) Even if families don't sit down to eat together as frequently as before, millions of Britons will nonetheless have got a share this weekend of one of that nation's great traditions; the Sunday roast. __1__ a cold winter's day, few culinary pleasures can 2 it. Yet as we report now, the food police are determined our health. That this__3__should be rendered yet another guilty pleasure __4__ to damage our health. The Food Standards Authority (FSA) has __5__ a public warning about the risks of a compound called acrylamide that forms in some foods cooked __6__ high temperatures. This means that people should __7__ crisping their roast potatoes, spurn thin-crust pizzas and only __8__ toast their bread. But where is the evidence to support such alarmist advice? __9__ studies have shown that acrylamide can cause neurological damage in mice, there is no __10__ evidence that it causes cancer in humans. Scientists say the compound is \"__11__ to be carcinogenic\" but have no hard scientific proof. __12__ the precautionary principle, it could be argued that it is __13__ to follow the FSA advice. __14__, it was rumored that smoking caused cancer for years before the evidence was found to prove a __15__. Doubtless a piece of boiled beef can always be __16__ up on Sunday alongside some steamed vegetables, without the Yorkshire pudding and no wine. But would life be worth living? __17__, the FSA says it is not telling people to cut out roast foods __18__, but to reduce their lifetime intake. However, their __19__ risks coming across as exhortation and nannying. Constant health scares just __20__ with no one listening. 1. [A] In [B] Towards [C] On [D] Till 2. [A] match [B] express [C] satisfy [D] influence 3. [A] patience [B] enjoyment [C] surprise [D] concern 4. [A] intensified [B] privileged [C] compelled [D] guaranteed 5. [A] issued [B] received [C] ignored [D] canceled 6. [A] under [B] at [C] for [D] by 7. [A] forget [B] regret [C] finish [D] avoid 8. [A] partially [B] regularly [C] easily [D] initially 9. [A] Unless [B] Since [C] If [D] While 10. [A] secondary [B] external [C] inconclusive [D] negative 11. [A] insufficient [B] bound [C] likely [D] slow 12. [A] On the basis of [B] At the cost of [C] In addition to [D] In contrast to 13. [A] interesting [B] advisable [C] urgent [D] fortunate 14. [A] As usual [B] In particular [C] By definition [D] After all 15. [A] resemblance [B] combination [C] connection [D] pattern 16. [A] made [B] served [C] saved [D] used 17. [A] To be fair [B] For instance [C] To be brief [D] in general 18. [A] reluctantly [B] entirely [C] gradually [D] carefully 19. [A] promise [B] experience [C] campaign [D] competition 20. [A] follow up [B] pick up [C] open up [D] end up\n" +
                "- 2 - Section Ⅱ Reading Comprehension Part A Directions: Read the following four texts. Answer the questions below each text by choosing A, B, C or D. Mark your answers on the ANSWER SHEET. （40 points） Text 1 A group of labour MPs, among them Yvette Cooper, are bringing in the new year with a call to institute a UK \"town of culture\" award. The proposal is that it should sit alongside the existing city of culture title, which was held by Hull in 2017 and has been awarded to Coventry for zoz1. Cooper and her colleagues argue that the success of the crown for Hull, where it brought in £220m of investment and an avalanche of arts, out not to be confined to cities. Britain' town, it is true are not prevented from applying, but they generally lack the resources to put together a bit to beat their bigger competitions. A town of culture award could, it is argued, become an annual event, attracting funding and creating jobs. Some might see the proposal as a boo by prize for the fact that Britain is no longer be able to apply for the much more prestigious title of European capital of culture, a sough-after award bagged by Glasgow in 1990 and Liverpool in 2008. A cynic might speculate that the UK is on the verge of disappearing into an endless fever of self-celebration in its desperation to reinvent itself for the post-Brexit world: after town of culture, who knows that will follow-village of culture? Suburb of culture? Hamlet of culture? It is also wise to recall that such titles are not a cure-all. A badly run \"year of culture\" washes in and out of a place like the tide, bringing prominence for a spell but leaving no lasting benefits to the community. The really successful holders of such titles are those that do a great deal more than fill hotel bedrooms and bring in high-profile arts events and good press for a year. They transform the aspirations of the people who live there; they nudge the self-image of the city into a bolder and more optimistic light. It is hard to get right, and requires a remarkable degree of vision, as well as cooperation between city authorities, the private sector, community. groups and cultural organisations. But it can be done: Glasgow's year as European capital of culture can certainly be seen as one of complex series of factors that have turned the city into the power of art, music and theatre that it remains today. A \"town of culture\" could be not just about the arts but about honoring a town's peculiarities-helping sustain its high street, supporting local facilities and above all celebrating its people and turn it into action. 21.Copper and her colleague argue that a \"town of culture\" award would ___. A. consolidate the town city ties in Britain B. promote cooperation among Brain's towns C. increase the economic strength of Brain's towns D. focus Brain's limited resources on cultural events. 22.According to paragraph 2, the proposal might be regarded by some as ______.. A. a sensible compromise B. a self-deceiving attempt C. an eye-catching bonus D. an inaccessible target 23. The author suggests that a title holder is successful only if it ______ A. endeavor to maintain its image B. meets the aspiration of its people C. brings its local arts to prominence\n" +
                "- 3 - D. commits to its long-term growth 24. “Glasgow” is mentioned in Paragraph 3 to present ______ A. a contrasting case B. a supporting example C. a background story D. a related topic 25. What is the author's attitude towards the proposal? A. Skeptical B. Objective C. Favorable D. Critical Text 2 Scientific publishing has long been a licence to print money. Scientists need joumals in which to publish their research, so they will supply the articles without monetary reward. Other scientists perform the specialised work of peer review also for free, because it is a central element in the acquisition of status and the production of scientific knowledge. With the content of papers secured for free, the publisher needs only find a market for its journal. Until this century, university libraries were not very price sensitive. Scientific publishers routinely report profit margins approaching 40% on their operations, at a time when the rest of the publishing industry is in an existential crisis. The Dutch giant Elsevier, which claims to publish 25% of the scientific papers produced in the world , made profits of more than £900m last year, while UK universities alone spent more than £210m in 2016 to enable researchers to access their own publicly funded research; both figures seem to rise unstoppably despite increasingly desperate efforts to change them. The most drastic, and thoroughly illegal, reaction has been the emergence of Sci-Hub, a kind of global photocopier for scientific papers, set up in 2012, which now claims to offer access to every paywalled article published since 2015. The success of Sci-Hub, which relies on researchers passing on copies they have themselves legally accessed, shows the legal ecosystem has lost legitimacy among its users and must be transformed so that it works for all participants. In Britain the move towards open access publishing has been driven by funding bodies. In some ways it has been very successful. More than half of all British scientific research is now published under open access terms: either freely available from the moment of publication, or paywalled for a year or more so that the publishers can make a profit before being placed on general release. Yet the new system has not worked out any cheaper for the universities. Publishers have responded to the demand that they make their product free to readers by charging their writers fees to cover the costs of preparing an article. These range from around ￡ 500 to $5,000. A report last year pointed out that the costs both of subscriptions and of these “article preparation costs” had been steadily rising at a rate above inflation. In some ways the scientific publishing model resembles the economy of the social internet: labour is provided free in exchange for the hope of status, while huge profits are made by a few big firms who run the market places. In both cases, we need a rebalancing of power. 26. Scientific publishing is seen as“a licence to print money\" partly because________ [A] its funding has enjoyed a steady increase . [B] its marketing strategy has been successful. [C] its payment for peer review is reduced. [D] its content acquisition costs nothing. 27. According to Paragraphs 2 and 3, scientific publishers Elsevier have________ [A] thrived mainly on university libraries. [B] gone through an existential crisis. [C] revived the publishing industry. [D] financed researchers generously.\n" +
                "- 4 - 28. How does the author feel about the success of Sci-Hub? [A] Relieved. [B] Puzzled. [C] Concerned [D] Encouraged. 29. It can be learned from Paragraphs 5 and 6 that open access terms________ [A]allow publishers some room to make money. [B] render publishing much easier for scientists. [C] reduce the cost of publication substantially. [D] free universities from financial burdens. 30. Which of the following characteristics the scientific publishing model? [A] Trial subscription is offered. [B] Labour triumphs over status. [C] Costs are well controlled. D] The few feed on the many. Text 3 Progressives often support diversity mandates as a path to equality and a way to level the playing field. But all too often such policies are an insincere form of virtue-signaling that benefits only the most privileged and does little to help average people. A pair of bills sponsored by Massachusetts state Senator Jason Lewis and House Speaker Pro Tempore Patricia Haddad, to ensure \"gender parity\" on boards and commissions, provide a case in point. Haddad and Lewis are concerned that more than half the state-government boards are less than 40 percent female. In order to ensure that elite women have more such opportunities, they have proposed imposing government quotas. If the bills become law, state boards and commissions will be required to set aside 50 percent of board seats for women by 2022. The bills are similar to a measure recently adopted in Califomia, which last year became the first state to require gender quotas for private companies. In signing the measure, California Governor Jerry Brown admitted that the law, which expressly classifies people on the basis of sex, is probably unconstitutional. The US Supreme Court frowns on sex-based classifications unless they are designed to address an \"important\" policy interest, Because the California law applies to all boards, even where there is no history of prior discrimination, courts are likely to rule that the law violates the constitutional guarantee of \"equal protection\". But are such government mandates even necessary? Female participation on corporate boards may not currently mirror the percentage of women in the general population, but so what? The number of women on corporate boards has been steadily increasing without government interference. According to a study by Catalyst, between 2010 and 2015 the share of women on the boards of global corporations increased by 54 percent. Requiring companies to make gender the primary qualification for board membership will inevitably lead to less experienced private sector boards. That is exactly what happened when Norway adopted a nationwide corporate gender quota. Writing in The New Republic, Alice Lee notes that increasing the number of opportunities for board membership without increasing the pool of qualified women to serve on such boards has led to a “golden skirt \"phenomenon, where the same elite women scoop up multiple seats on a variety of boards. Next time somebody pushes corporate quotas as a way to promote gender equity, remember that such policies are largely self-serving measures that make their sponsors feel good but do little to help average women. 31. The author believes that the bills sponsored by Lewis and Haddad wills________\n" +
                "- 5 - [A] help little to reduce gender bias. [B] pose a threat to the state government. [C] raise women's position in politics. [D] greatly broaden career options. 32. Which of the following is true of the California measure? [A] It has irritated private business owners. [B] It is welcomed by the Supreme Court, [C] It may go against the Constitution. [D] It will settle the prior controversies. 33. The author mentions the study by Catalyst to illustrate____ [A] the harm from arbitrary board decision. [B] the importance of constitutional guarantees. [C] the pressure on women in global corporations. [D] the needlessness of government interventions. 34. Norway's adoption of a nationwide corporate gender quota has led to____ [A] the underestimation of elite women's role. [B] the objection to female participation on boards. [C] the entry of unqualified candidates into the board. [D] the growing tension between labor and management. 35. Which of the following can be inferred from the text? [A] Women's need in employment should be considered. [B] Feasibility should be a prime concern in policymaking. [C] Everyone should try hard to promote social justice. [D] Major social issues should be the focus of legislation. Text 4 Last Thursday, the French Senate passed a digital services tax, which would impose an entirely new tax on large multinationals that provide digital services to consumers or users in France. Digital services include everything from providing a platform for selling goods and services online to targeting advertising based on user data, and the tax applies to gross revenue from such servces. Many French politicians and media outlets have referred to this as a“GAFA tax,\" meaning that it is designed to apply primarily to companies such as Google, Apple, Facebook and Amazon- in other words, multinational tech companies based in the United States. The digital services tax now awaits the signature of President Emmanuel Macron, who has expressed support for the measure, and it could go into effect within the next few weeks. But it has already sparked significant controversy, with the Unite Sates trade representative opening an investigation into whether the tax discriminates against American companies, which in turn could lead to trade sanctions against France. The French tax is not just a unilateral move by one country in need of revenue. Instead, the digital services tax is part of a much larger trend, with countries over the past few years proposing or putting in place an alphabet soup of new international tax provisions. These have included Britain's DPT (diverted profits tax), Australia's MAAL (multinational antiavoidance law), and India's SEP (significant economic presence) test, to name but a few. At the same time, the European Union, Spain, Britain and several other countries have all seriously contemplated digital services taxes. These unilateral developments differ in their specifics, but they are all designed to tax multinationals on income and revenue that countries believe they should have a right to tax, even if international tax rules do not grant them that right. In other words, they all share a view that the international tax system has failed to keep up with the current economy. In response to these many unilateral measures, the Organization for Economic Cooperation and Development\n" +
                "- 6 - (OECD) is currently working with 131 countries to reach a consensus by the end of 2020 on an international solution. Both France and the United States are involved in the organization' s work, but France's digital services tax and the American response raise questions about what the future holds for the international tax system. France`s planned tax is a clear warning: Unless a broad consensus can be reached on reforming the international tax system, other nations are likely to follow suit, and American companies will face a cascade of different taxes from dozens of nations that will prove burdensome and costly. 36. The French Senate has passed a bill to_____ [A] regulate digital services platforms. [B] protect French companies' interests. [C] impose a levy on tech multinationals. [D] curb the influence of advertising. 37. It can be learned from Paragraph 2 that the digital services tax _____ [A] may trigger countermeasures against France. [B] is apt to arouse criticism at home and abroad. [C] aims to ease international trade tensions. [D] will prompt the tech giants to quit France. 38. The countries adopting the unilateral measures share the opinion that _____ [A] redistribution of tech giants' revenue must be ensured. [B] the current international tax system needs upgrading. [C] tech multinationals' monopoly should be prevented. [D] all countries ought to enjoy equal taxing rights. 39. It can be learned from Para 5 that the OECO's current work_____ [A] is being resisted by US companies. [B] needs to be readjusted immediately. [C] is faced with uncertain prospects. [D] needs to in involve more countries. 40. Which of the following might be the. best title for this text? [A] France Is Confronted with Trade Sanctions [B] France leads the charge on Digital Tax [C] France Says \"NO\" to Tech Multinationals [D] France Demands a Role in the Digital Economy Part B Directions: In the following text, some sentences have been removed. For Questions 41 -45, choose the most suitable one from the fist A-G to fit into each of the numbered blanks. There are two extra choices, which do not fit in any of the gaps. Mark your answers on ANSWER SHEET. (10 points) [A] Eye fixactions are brief [B] Too much eye contact is instinctively felt to be rude [C] Eye contact can be a friendly social signal [D] Personality can affect how a person reacts to eye contact [E] Biological factors behind eye contact are being investigated [F] Most people are not comfortable holding eye contact with strangers [G] Eye contact can also be aggressive. In a social situation, eye contact with another person can show that you are paying attention in a friendly way.\n" +
                "- 7 - But it can also be antagonistic such as when a political candidate tums toward their competitor during a debate and makes eye contact that signals hostility. Here 's what hard science reveals about eye contact: 41. ________________ We know that a typical infant will instinctively gaze into its mother's eyes, and she will look back . This mutual gaze is a major part of the attachment between mother and child. In adulthood, looking someone else in a pleasant way can be a complimentary sign of paying attention. It can catch someone's attention in a crowded room, \"Eye contact and smile\" can signal availability and confidence, a common-sense notion supported in studies by psychologist Monica Moore. 42.________ Neuroscientist Bonnie Augeung found that the hormone oxytocin increased the amount of eye contact from men toward the interviewer during a brief interview when the direction of their gaze was recorded. This was also found in high- functioning men with some autistic spectrum symptoms, who may tend to avoid eye contact. Specific brain regions that respond during direct gaze are being explored by other researches, using advanced methods of brain scanning. 43.________ With the use of eye-tracking technology, Julia Minson of the Harvard Kennedy School of Government concluded that eye contact can signal very different kinds of messages, depending on the situation While eye contact may be a sign of connection or trust in friendly situations, it's more likely to be associated with dominance OF intimidation in adversarial situations. Whether you're a politician or a parent, it might be helpful to keep 'in mind that trying to maintain eye contact may backfire if you're trying to convince someone who has a different set of beliefs than you,\" said Minson. 44.________ When we look at a face or a picture, our eyes pause on one spot at a time, often on the eyes or mouth. These pauses typically occur at about three per second, and the eyes then jump to another spot, until several important points in the image are registered like a series of snapshots. How the whole image is then assembled and perceived is still a mystery although it is the subject of current research. 45.________ In people who score high in a test of neuroticism, a personality dimension associated with self-consciousness and anxiety, eye contact triggered more activity associated with avoidance, according to the Finnish researcher Jari Hietanen and colleagues. Our findings indicate that people do not only feel different when they are the centre of attention but that their brain reactions also differ-\" A more direct finding is that people who scored high for negative emotions like anxiety looked at others for shorter periods of time and reported more comfortable feelings when others did not look directly at them. Part C Translation Directions: Read the following text carefully and then translate the underlined segments into Chinese. Your translation should be written neatly on the ANSWER SHEET. (10 points) Following the explosion of creativity in Florence during the 14th century known as the Renaissance, the modern world saw a departure from what it had once known. It turned from God and the authority of the Roman Catholic Church and instead favoured a more humanistic approach to being. Renaissance ideas had spread throughout Europe well into the 17th century, with the arts and sciences flourishing extraordinarily among those with a more logical disposition. 46.With（the gap between）the church's teachings and ways of thinking being eclipsed by the Renaissance, the gap between the medieval and modern periods had been bridged, leading to new and unexplored intellectual territories.\n" +
                "- 8 - During the Renaissance, the great minds of Nicolaus Copernicus, Johannes Kepler and Galileo Galilei demonstrated the power of scientific study and discovery. 47. Before each of their revelations, many thinkers at the time had sustained more ancient ways of thinking, including the geocentric view that the Earth was at the centre of our universe. Copernicus theorized in 1543 that in actual fact, all of the planets that we knew of revolved not around the Earth, but the Sun, a system that was later upheld by Galileo at his own expense. Offering up such a theory during a time of high tension between scientific and religious minds was branded as heresy, and any such heretics that continued to spread these lies were to be punished by imprisonment or even death. Galileo was excommunicated by the Church and imprisoned for life for his astronomical observations and his support of the heliocentric principle. 48. Despite attempts by the Church to strong-arm this new generation of logicians and rationalists, more explanations for how the universe functioned were being made, and at a rate that the people-including the Church -could no longer ignore. It was with these great revelations that a new kind of philosophy founded in reason was born.The Church's long-standing dogma was losing the great battle for truth to rationalists and scientists. This very fact embodied the new ways of thinking that swept through Europe during most of the 17th century. 49. As many took on the duty of trying to integrate reasoning and scientific philosophies into the world. The Renaissance was over and it was time for a new era-the Age of Reason. The 17th and 18th centuries were times of radical change and curiosity. Scientific method, reductionism and the questioning of Church ideals was to be encouraged, as were ideas of liberty, tolerance and progress. 50. Such actions to seek knowledge and to understand what information we already knew were captured by the Latin phrase 'sapere aude ' or ' dare to know', after Immanuel Kant used it in his essay An Answer to the Question: What is Enlightenment? It was the purpose and responsibility of great minds to go forth and seek out the truth, which they believed to be founded in knowledge.Section IV Writing Part A 51.Directions: The Student Union of your university has assigned you to inform theinternational students an upcoming singing contest. Write a notice in about100 words. Write your answer on the ANSWER SHEET. Do not use your name in the notice. Part B 52: Directions: Write an essay of 160-200 words based on the picture below. In your essay, you should: 1) Describe the picture briefly; 2) Interpret the implied meaning, and 3) Give your comments");
        test1.setEditable(false);
        test1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        test1.setBackground(new Color(165, 222, 228));
        test1.setFont(new Font("雅黑",Font.PLAIN,16));
        test1.setLineWrap(true);
        test1.setWrapStyleWord(true);
        jp=new JScrollPane(test1);

        test2=new JTextArea("\n\nIt is true that many older people believe in traditional values that often seem incompatible with the needs of younger people. While I agree that some traditional ideas are outdated, I believe that others are still useful and should not be forgotten.\n" +
                "On the one hand, many of the ideas that elderly people have about life are becoming less relevant for younger people. In the past, for example, people were advised to learn a profession and find a secure job for life, but today’s workers expect much more variety and diversity from their careers. At the same time, the ‘rules’ around relationships are being eroded as young adults make their own choices about who and when to marry. But perhaps the greatest disparity between the generations can be seen in their attitudes towards gender roles. The traditional roles of men and women, as breadwinners and housewives, are no longer accepted as necessary or appropriate by most younger people.\n" +
                "On the other hand, some traditional views and values are certainly applicable to the modern world. For example, older generations attach great importance to working hard, doing one’s best, and taking pride in one’s work, and these behaviours can surely benefit young people as they enter today’s competitive job market. Other characteristics that are perhaps seen as traditional are politeness and good manners. In our globalised world, young adults can expect to come into contact with people from a huge variety of backgrounds, and it is more important than ever to treat others with respect. Finally, I believe that young people would lead happier lives if they had a more ‘old-fashioned’ sense of community and neighbourliness.\n" +
                "In conclusion, although the views of older people may sometimes seem unhelpful in today’s world, we should not dismiss all traditional ideas as irrelevant."+"\n\nIt is true that many older people believe in traditional values that often seem incompatible with the needs of younger people. While I agree that some traditional ideas are outdated, I believe that others are still useful and should not be forgotten.\n" +
                "On the one hand, many of the ideas that elderly people have about life are becoming less relevant for younger people. In the past, for example, people were advised to learn a profession and find a secure job for life, but today’s workers expect much more variety and diversity from their careers. At the same time, the ‘rules’ around relationships are being eroded as young adults make their own choices about who and when to marry. But perhaps the greatest disparity between the generations can be seen in their attitudes towards gender roles. The traditional roles of men and women, as breadwinners and housewives, are no longer accepted as necessary or appropriate by most younger people.\n" +
                "On the other hand, some traditional views and values are certainly applicable to the modern world. For example, older generations attach great importance to working hard, doing one’s best, and taking pride in one’s work, and these behaviours can surely benefit young people as they enter today’s competitive job market. Other characteristics that are perhaps seen as traditional are politeness and good manners. In our globalised world, young adults can expect to come into contact with people from a huge variety of backgrounds, and it is more important than ever to treat others with respect. Finally, I believe that young people would lead happier lives if they had a more ‘old-fashioned’ sense of community and neighbourliness.\n" +
                "In conclusion, although the views of older people may sometimes seem unhelpful in today’s world, we should not dismiss all traditional ideas as irrelevant.");
        test2.setEditable(false);
        test2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        test2.setBackground(new Color(165, 222, 228));
        test2.setFont(new Font("雅黑",Font.PLAIN,16));
        test2.setLineWrap(true);
        test2.setWrapStyleWord(true);
        jp2=new JScrollPane(test2);

        test3=new JTextArea("\n\nIt is true that ex-prisoners can become normal, productive members of society. I completely agree with the idea that allowing such people to speak to teenagers about their experiences is the best way to discourage them from breaking the law.\n" +
                "In my opinion, teenagers are more likely to accept advice from someone who can speak from experience. Reformed offenders can tell young people about how they became involved in crime, the dangers of a criminal lifestyle, and what life in prison is really like. They can also dispel any ideas that teenagers may have about criminals leading glamorous lives. While adolescents are often indifferent to the guidance given by older people, I imagine that most of them would be extremely keen to hear the stories of an ex-offender. The vivid and perhaps shocking nature of these stories is likely to have a powerful impact.\n" +
                "The alternatives to using reformed criminals to educate teenagers about crime would be much less effective. One option would be for police officers to visit schools and talk to young people. This could be useful in terms of informing teens about what happens to lawbreakers when they are caught, but young people are often reluctant to take advice from figures of authority. A second option would be for school teachers to speak to their students about crime, but I doubt that students would see teachers as credible sources of information about this topic. Finally, educational films might be informative, but there would be no opportunity for young people to interact and ask questions.\n" +
                "In conclusion, I fully support the view that people who have turned their lives around after serving a prison sentence could help to deter teenagers from committing crimes."+"\n\nIt is true that ex-prisoners can become normal, productive members of society. I completely agree with the idea that allowing such people to speak to teenagers about their experiences is the best way to discourage them from breaking the law.\n" +
                "In my opinion, teenagers are more likely to accept advice from someone who can speak from experience. Reformed offenders can tell young people about how they became involved in crime, the dangers of a criminal lifestyle, and what life in prison is really like. They can also dispel any ideas that teenagers may have about criminals leading glamorous lives. While adolescents are often indifferent to the guidance given by older people, I imagine that most of them would be extremely keen to hear the stories of an ex-offender. The vivid and perhaps shocking nature of these stories is likely to have a powerful impact.\n" +
                "The alternatives to using reformed criminals to educate teenagers about crime would be much less effective. One option would be for police officers to visit schools and talk to young people. This could be useful in terms of informing teens about what happens to lawbreakers when they are caught, but young people are often reluctant to take advice from figures of authority. A second option would be for school teachers to speak to their students about crime, but I doubt that students would see teachers as credible sources of information about this topic. Finally, educational films might be informative, but there would be no opportunity for young people to interact and ask questions.\n" +
                "In conclusion, I fully support the view that people who have turned their lives around after serving a prison sentence could help to deter teenagers from committing crimes.");
        test3.setEditable(false);
        test3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        test3.setBackground(new Color(165, 222, 228));
        test3.setFont(new Font("雅黑",Font.PLAIN,16));
        test3.setLineWrap(true);
        test3.setWrapStyleWord(true);
        jp3=new JScrollPane(test3);
    }//就是有点丑
    private void initButton(){//初始化左侧button
        leftPanel=new JPanel();
        leftPanel.setBackground(Color.white);
        leftPanel.setSize(600,300);
        leftPanel.setLayout(new GridLayout(4,1));
        first=new JButton("第一套");
        second=new JButton("第二套");
        third=new JButton("第三套");
        BackButton = new JButton("返回");
        first.setBorderPainted(false);first.setFocusPainted(false);first.setBackground(new Color(165, 222, 228));first.setForeground(Color.white);
        second.setBorderPainted(false);second.setFocusPainted(false);second.setBackground(new Color(168, 148, 199));second.setForeground(Color.white);
        third.setBorderPainted(false);third.setFocusPainted(false);third.setBackground(new Color(168, 148, 199));third.setForeground(Color.white);
        BackButton.setBorderPainted(false);BackButton.setFocusPainted(false);BackButton.setBackground(new Color(168, 148, 199));BackButton.setForeground(Color.white);
        leftPanel.add(first);leftPanel.add(second);leftPanel.add(third);leftPanel.add(BackButton);
        leftPanel.setBorder(new EmptyBorder(0,0,0,0));
    }
    private void initPanel() {
        initText();
        article1 = new JPanel();article1.setBorder(new EmptyBorder(0,0,0,0));
        article2 = new JPanel();article2.setBorder(new EmptyBorder(0,0,0,0));
        article3 = new JPanel();article3.setBorder(new EmptyBorder(0,0,0,0));
        articleMain=new JPanel();articleMain.setBorder(new EmptyBorder(0,0,0,0));
        thisLayout=new CardLayout();
        articleMain.setLayout(thisLayout);
        articleMain.setBackground(new Color(165, 222, 228));
        article1.setBackground(new Color(165, 222, 228));
        article2.setBackground(new Color(165, 222, 228));
        article3.setBackground(new Color(165, 222, 228));
        articleMain.setSize(600,500);
        article1.setSize(600,500);
        article2.setSize(600,500);
        article3.setSize(600,500);

        articleMain.add("first",article1);
        articleMain.add("second",article2);
        articleMain.add("third",article3);

        article1.setLayout(new GridLayout());article2.setLayout(new GridLayout());article3.setLayout(new GridLayout());
        article1.add(jp);//加入测试文字
        article2.add(jp2);
        article3.add(jp3);
    }
    private void initLayout(){
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0,0};  //设置了总共有一列
        gridBagLayout.rowHeights = new int[]{0};  //设置了总共有2行
        gridBagLayout.columnWeights = new double[]{0.15,0.85};  //设置了列的宽度为容器宽度
        gridBagLayout.rowWeights = new double[]{1.0};  //第一行的高度占了容器的2份，第二行的高度占了容器的8份
        setLayout(gridBagLayout);

        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        add(leftPanel, gbc_panel);

        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 0;
        add(articleMain, gbc_panel_1);

        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                first.setBackground(new Color(165, 222, 228));
                second.setBackground(new Color(168, 148, 199));
                third.setBackground(new Color(168, 148, 199));
                thisLayout.show(articleMain,"first");
            }
        });
        second.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                first.setBackground(new Color(168, 148, 199));
                second.setBackground(new Color(165, 222, 228));
                third.setBackground(new Color(168, 148, 199));
                thisLayout.show(articleMain,"second");
            }
        });
        third.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                first.setBackground(new Color(168, 148, 199));
                second.setBackground(new Color(168, 148, 199));
                third.setBackground(new Color(165, 222, 228));
                thisLayout.show(articleMain,"third");
            }
        });
    }
    private void initGUI() {
        initButton();
        initPanel();
        initLayout();

        this.setBackground(Color.cyan);
    }
    private void initPopMenu(){//初始化菜单
        test1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3) {
                    //弹出右键菜单
                    popMenu pp=new popMenu();
                    pp.show(e.getComponent(), e.getX(), e.getY());
                    pp.search.addActionListener(new ActionListener() {//弹出查询的单词
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                            String selected=test1.getSelectedText();
                            Word w=new searchWord().getWordByName(selected);
                            if(w==null){
                                JFrame newPage=new JFrame();
                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(new JLabel("未查到单词"+selected));
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }else{
                                Font FB=new Font("雅黑",Font.BOLD,16);
                                Font F=new Font("雅黑",Font.PLAIN,10);
                                String WORD=w.getFrontSide();
                                String MEAN=w.getBackSide();
                                String SEN=w.getEgSentence();
                                JLabel j1=new JLabel(WORD);j1.setFont(FB);
                                JLabel j2=new JLabel(MEAN);j2.setFont(F);
                                JFrame newPage=new JFrame();
                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(j1);newPage.add(j2);
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }
                        }
                    });
                }
            }


        });
        test2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3) {
                    //弹出右键菜单
                    popMenu pp=new popMenu();
                    pp.show(e.getComponent(), e.getX(), e.getY());
                    pp.search.addActionListener(new ActionListener() {//弹出查询的单词
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                            String selected=test1.getSelectedText();
                            Word w=new searchWord().getWordByName(selected);
                            System.out.println("查单词啦");
                            if(w==null){
                                JFrame newPage=new JFrame();
                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(new JLabel("未查到单词"+selected));
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }else{
                                Font FB=new Font("雅黑",Font.BOLD,16);
                                Font F=new Font("雅黑",Font.PLAIN,10);
                                String WORD=w.getFrontSide();
                                String MEAN=w.getBackSide();
                                String SEN=w.getEgSentence();
                                JLabel j1=new JLabel(WORD);j1.setFont(FB);
                                JLabel j2=new JLabel(MEAN);j2.setFont(F);
                                JFrame newPage=new JFrame();
                                JPanel wordp=new JPanel();wordp.add(j1);
                                JPanel meanp=new JPanel();meanp.add(j2);

                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(wordp);newPage.add(meanp);
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }
                        }
                    });
                }
            }


        });
        test3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3) {
                    //弹出右键菜单
                    popMenu pp=new popMenu();
                    pp.show(e.getComponent(), e.getX(), e.getY());
                    pp.search.addActionListener(new ActionListener() {//弹出查询的单词
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                            String selected=test1.getSelectedText();
                            Word w=new searchWord().getWordByName(selected);
                            if(w==null){
                                JFrame newPage=new JFrame();
                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(new JLabel("未查到单词"+selected));
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }else{
                                Font FB=new Font("雅黑",Font.BOLD,16);
                                Font F=new Font("雅黑",Font.PLAIN,10);
                                String WORD=w.getFrontSide();
                                String MEAN=w.getBackSide();
                                String SEN=w.getEgSentence();
                                JLabel j1=new JLabel(WORD);j1.setFont(FB);
                                JLabel j2=new JLabel(MEAN);j2.setFont(F);
                                JFrame newPage=new JFrame();
                                newPage.setLayout(new GridLayout(2,1));
                                newPage.setSize(300,200);
                                newPage.add(j1);newPage.add(j2);
                                newPage.setLocation(e.getX(),e.getY());
                                newPage.show();
                            }
                        }
                    });
                }
            }


        });

    }
    //Yuxin Zhu: construction func, DON'T change this func unless necessary
    public PracticePage() {
        initGUI();
        initPopMenu();
    }
    public static void main(String[] args){
        JFrame jf=new JFrame();
        jf.add(new PracticePage());
        jf.setSize(800,600);
        jf.show();
    }
}
class searchWord{
    public Word getWordByName(String wordname){
        String URL = "https://cdn.jsdelivr.net/gh/lyc8503/baicizhan-word-meaning-API/data/words/"+wordname+".json";//地址
        HttpURLConnection conn = null;
        BufferedReader dataIn = null;
        try {
            java.net.URL url = new java.net.URL(URL);

            // 将url以open方法返回的urlConnection连接强转为HttpURLConnection连接(标识一个url所引用的远程对象连接)
            // 此时cnnection只是为一个连接对象,待连接中
            conn = (HttpURLConnection) url.openConnection();

            // 设置连接输入流为true
            conn.setDoInput(true);
            // 设置请求方式为get
            conn.setRequestMethod("GET");
            // get请求缓存设为false
            conn.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            conn.setInstanceFollowRedirects(true);

            // 设置内容的类型,设置为经过urlEncoded编码过的from参数
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setRequestProperty("accept", "application/xml");

            // 建立连接
            // (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            conn.connect();

            // 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
            // 若找不到该单词
            if (conn.getInputStream()==null){
                JOptionPane.showMessageDialog(null,"没有找到这个单词哦，您可以试试其他的单词呢~","查询失败",2);
                return new Word("","","","","");
            }

            dataIn = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            sb.append(dataIn.readLine());

            //将结果转换为JSON对象，并获得Word的属性值
            JSONObject jb = new JSONObject(sb.toString());
            String accent = StringEscapeUtils.unescapeJava(jb.getString("accent"));
            String mean_cn = StringEscapeUtils.unescapeJava(jb.getString("mean_cn"));
            String mean_en = jb.getString("mean_en");
            String sentence = jb.getString("sentence");
            //当找不到例句时
            if (sentence == null){
                sentence = "啊亲爱的例句，你在哪里~";
            }
            String sentence_trans = jb.getString("sentence_trans");

            return new Word(wordname, mean_cn,sentence,sentence_trans,accent);

        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            try {
                // 重要且易忽略步骤 (关闭流,切记!)
                if (dataIn != null) {
                    dataIn.close();
                }
                // 销毁连接
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}